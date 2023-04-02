package com.leedsbeckett.studentportal.Controller;

import com.leedsbeckett.studentportal.Entity.Student;
import com.leedsbeckett.studentportal.Models.AccountModel;
import com.leedsbeckett.studentportal.Models.CourseModel;
import com.leedsbeckett.studentportal.Models.StudentModel;
import com.leedsbeckett.studentportal.Service.IntegrationService;
import com.leedsbeckett.studentportal.Service.StudentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

@Controller
public class AuthenticationController {

    private StudentService studentService;
    private IntegrationService integrationService;

    public AuthenticationController(StudentService studentService, IntegrationService integrationService){
        this.studentService = studentService;
        this.integrationService =integrationService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }
   @GetMapping("/")
    public String form(){
        return "login";
    }

    // handler method to handle user registration form request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        StudentModel studentModel = new StudentModel();
        model.addAttribute("studentModel", studentModel);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("student") StudentModel studentModel,
                               BindingResult result,
                               Model model){
        Student existingStudent = studentService.findStudentByEmail(studentModel.getEmail());

        if(existingStudent != null && existingStudent.getEmail() != null && !existingStudent.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("mmddhhmmss");
        String todayAsString = dateFormat.format(date);
        studentModel.studentId = "C" + todayAsString;
        studentModel.setStudentId(studentModel.getStudentId());
        if(result.hasErrors()){
            model.addAttribute("student", studentModel);
            return "/register";
        }

        studentService.saveStudent(studentModel);



        return "redirect:/register?success";
    }

    @GetMapping("/students")
    public String studentList(Model model){
        List<StudentModel> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/profile")
    public String getprofile(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var name = auth.getName();
        var students = studentService.findStudentByEmail(name);
        model.addAttribute("studentprofile",students);
        return "profile";

    }

    @PostMapping("/profileupdate/{id}")
    public String updateProfile(Model model,@PathVariable Long id, @ModelAttribute("student") StudentModel studentModel){
        Student currentStudent = studentService.getStudentById(id);
        ModelMapper modelMapper = new ModelMapper();
        currentStudent.setFirstName(studentModel.getFirstName());
        currentStudent.setLastName(studentModel.getLastName());

        modelMapper.map(currentStudent, studentModel);
        studentService.updateStudent(studentModel);
        var successMsg = "Profile updated";
        model.addAttribute("successMsg",successMsg);
        return "redirect:/index";

    }

    @GetMapping("/profilesuccess")
    public String getEditStudent(Model model,@PathVariable Long id, @ModelAttribute("student") StudentModel studentModel) {;
        return "profileupdate";
    }
    @PostMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") StudentModel studentModel,
                               Model model){
        Student existingStudent = studentService.findStudentByEmail(studentModel.getEmail());
        ModelMapper modelMapper = new ModelMapper();

        if(existingStudent != null && existingStudent.getEmail() != null && !existingStudent.getEmail().isEmpty()){
            existingStudent.setFirstName(studentModel.getFirstName());
            existingStudent.setLastName(studentModel.getLastName());

            modelMapper.map(existingStudent, studentModel);
            studentService.updateStudent(studentModel);
        }
        return "redirect:/updateStudent?success";
    }

    @RequestMapping(value = "/myusername", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}

