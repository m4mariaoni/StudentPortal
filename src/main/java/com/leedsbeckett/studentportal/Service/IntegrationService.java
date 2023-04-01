package com.leedsbeckett.studentportal.Service;

import com.leedsbeckett.studentportal.Entity.Student;
import com.leedsbeckett.studentportal.Models.AccountModel;
import com.leedsbeckett.studentportal.Models.InvoiceModel;
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
        return restTemplate.postForObject("https://localhost:7179/api/Account/" , accountModel, AccountModel.class);
    }
    public Student createLibraryAccount(Student student){
        return restTemplate.postForObject("https://localhost:7019/api/User/CreateAccount/" , student, Student.class);
    }
    public InvoiceModel createCourseFeeInvoice(InvoiceModel model){
        return restTemplate.postForObject("https://localhost:7179/api/Invoice/", model, InvoiceModel.class);
    }


}
