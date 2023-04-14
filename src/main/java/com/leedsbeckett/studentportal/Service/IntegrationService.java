package com.leedsbeckett.studentportal.Service;

import com.leedsbeckett.studentportal.Models.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IntegrationService {
    private final RestTemplate restTemplate;

    public IntegrationService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public AccountModel createFinanceAccount(AccountModel accountModel)
    {
        return restTemplate.postForObject("http://localhost:5179/api/Account/" , accountModel, AccountModel.class);
    }
    public LibraryModel createLibraryAccount(StudentModel student){
        LibraryModel model = new LibraryModel();
        model.Password = student.getPassword();
        model.Username = student.getStudentId();
        model.Role=0;
        model.Email = student.getEmail();
        model.FirstName = student.getFirstName();
        model.isAuthenticated=true;
        model.LastName = student.getLastName();

        return restTemplate.postForObject("http://localhost:5019//api/User/CreateAccount/" , model, LibraryModel.class);
    }
    public InvoiceViewModel createCourseFeeInvoice(InvoiceModel model){
        return restTemplate.postForObject("http://localhost:5179/api/Invoice/", model, InvoiceViewModel.class);
    }

    public AccountViewModel getOutstandingBalanceDetails(String studentId){

        return restTemplate.getForObject("http://localhost:5179/api/Account/student/{studentId}",AccountViewModel.class,studentId);
    }

}
