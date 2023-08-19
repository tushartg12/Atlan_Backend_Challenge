package com.atlan.backend.service;

import com.atlan.backend.entity.CustomerDetails;
import com.atlan.backend.entity.MessageTemplate;
import com.atlan.backend.entity.ValidationResponse;
import com.atlan.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ValidateService validateService;
    @Autowired
    SmsService smsService;
    public List<CustomerDetails> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Object addCustomer(CustomerDetails customerDetails) {
        ValidationResponse response=validateService.validateCustomer(customerDetails);
        if(response.isEmailValidity()&&response.isIncomeValidity()&& response.isMobileNumberValidity()&&response.isNameValidity()){
            customerRepository.save(customerDetails);
            MessageTemplate messageTemplate=new MessageTemplate();
            messageTemplate.setMobileNumber("+91"+customerDetails.getMobileNumber());
            messageTemplate.setMessgeBody(
                    "Your Unique ID is "+ customerDetails.getId()+"\n"+
                            "Your registered email is "+ customerDetails.getEmail()+"\n"+
                            "Your registered name is "+ customerDetails.getName()+"\n"+
                            "Your monthly income is "+ customerDetails.getMonthlyIncome()+"\n"+
                            "Your monthly savings are "+ customerDetails.getMonthlySavings()+"\n"+
                            "Your registered mobile number is "+ customerDetails.getMobileNumber()+"\n"
            );
            smsService.sendMessage(messageTemplate);
            return "Customer added successfully";
        }
        else{
            return response;
        }
    }
}
