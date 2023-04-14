package com.leedsbeckett.studentportal.Controller;

import com.leedsbeckett.studentportal.Entity.Course;
import com.leedsbeckett.studentportal.Entity.Student;
import com.leedsbeckett.studentportal.Exceptions.CourseNotFoundException;
import com.leedsbeckett.studentportal.Models.*;
import com.leedsbeckett.studentportal.Service.CourseService;
import com.leedsbeckett.studentportal.Service.IntegrationService;
import com.leedsbeckett.studentportal.Service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CourseController {
    private CourseService courseService;
    private StudentService studentService;
    private IntegrationService integrationService;
    public CourseController(CourseService courseService, StudentService studentService, IntegrationService integrationService){
        this.courseService = courseService;
        this.studentService = studentService;
        this.integrationService = integrationService;
    }
    @GetMapping("/courses")
    public String CourseList(Model model){
        List<CourseModel> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/courses/details/{id}")
    public String ShowCourse(@PathVariable("id") Long ids, Model model, RedirectAttributes ra) {
        try {
            CourseModel course = courseService.getbyId(ids);
            model.addAttribute("course", course);
            return "coursedetails";
        } catch (CourseNotFoundException e) {
            ra.addFlashAttribute("message", "Check this out");
            return "redirect:/courses";
        }
    }

    @PostMapping("/enroll/{id}")
    public String Enroll(@PathVariable("id") Long ids, Model model, RedirectAttributes ra) {
        try {

            InvoiceModel invoiceModel= new InvoiceModel();
            AccountModel accountModel = new AccountModel();

            var course = courseService.getCoursebyId(ids);


            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            var name = auth.getName();
            var user = studentService.findStudentByEmail(name);

            //Enroll student to course using the studentId with the courseId
           studentService.enrollStudentToCourse(user.id, ids);

            accountModel.studentId = user.getStudentId();

            //Create an invoice for the tuition fee where 1 represent tuition
            invoiceModel.setStudentId(user.getStudentId());
            invoiceModel.setAmount(course.getFee());
            invoiceModel.setType(1);
            invoiceModel.setDueDate(LocalDate.now().plusDays(5));
            var result = integrationService.createCourseFeeInvoice(invoiceModel);
            var referenceNo = result.getReference();
            model.addAttribute("course", course);
            model.addAttribute("refNo",referenceNo);
            return "enrolledcourse";
            //return "redirect:/enrolledcourse?success";
        } catch (CourseNotFoundException e) {
            ra.addFlashAttribute("message", "An error has occured. Please try again");
            return "redirect:/courses";
        }
    }

    @GetMapping("/graduation")
    public String graduationInfo(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var name = auth.getName();
        var user = studentService.findStudentByEmail(name);
        var status = integrationService.getOutstandingBalanceDetails(user.getStudentId());
        model.addAttribute("status", status);
        return "graduation";
    }


}
