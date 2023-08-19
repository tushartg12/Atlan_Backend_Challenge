package com.atlan.backend.service;

import com.atlan.backend.utils.TestUtils;
import com.atlan.backend.entity.CustomerDetails;
import com.atlan.backend.entity.ValidationResponse;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ValidateServiceTest {
    @InjectMocks ValidateService validateService;
    @InjectMocks
    TestUtils testUtils;
    @Test
    @Description("Happy Path")
    void validateCustomer_Test1() {
        CustomerDetails customerDetails=testUtils.getCustomerDetails();
        customerDetails.setMobileNumber("8005791568");
        ValidationResponse validationResponse=new ValidationResponse();
        validationResponse.setEmailValidity(true);
        validationResponse.setIncomeValidity(true);
        validationResponse.setNameValidity(true);
        validationResponse.setMobileNumberValidity(true);
        assertEquals(validationResponse,validateService.validateCustomer(customerDetails));
    }
    @Test
    @Description("Name is not valid")
    void validateCustomer_Test2() {
        CustomerDetails customerDetails=testUtils.getCustomerDetails();
        customerDetails.setMobileNumber("8005791568");
        customerDetails.setName("Name2");
        ValidationResponse validationResponse=new ValidationResponse();
        validationResponse.setEmailValidity(true);
        validationResponse.setIncomeValidity(true);
        validationResponse.setNameValidity(false);
        validationResponse.setMobileNumberValidity(true);
        assertEquals(validationResponse,validateService.validateCustomer(customerDetails));
    }
    @Test
    @Description("Email is not valid")
    void validateCustomer_Test3() {
        CustomerDetails customerDetails=testUtils.getCustomerDetails();
        customerDetails.setMobileNumber("8005791568");
        customerDetails.setEmail("emailxxxx");
        ValidationResponse validationResponse=new ValidationResponse();
        validationResponse.setEmailValidity(false);
        validationResponse.setIncomeValidity(true);
        validationResponse.setNameValidity(true);
        validationResponse.setMobileNumberValidity(true);
        assertEquals(validationResponse,validateService.validateCustomer(customerDetails));
    }
    @Test
    @Description("Mobile Number is not valid")
    void validateCustomer_Test4() {
        CustomerDetails customerDetails=testUtils.getCustomerDetails();
        ValidationResponse validationResponse=new ValidationResponse();
        validationResponse.setEmailValidity(true);
        validationResponse.setIncomeValidity(true);
        validationResponse.setNameValidity(true);
        validationResponse.setMobileNumberValidity(false);
        assertEquals(validationResponse,validateService.validateCustomer(customerDetails));
    }
    @Test
    @Description("When Savings are greater than Income")
    void validateCustomer_Test5() {
        CustomerDetails customerDetails=testUtils.getCustomerDetails();
        customerDetails.setMobileNumber("8005791568");
        customerDetails.setMonthlyIncome(1000);
        customerDetails.setMonthlySavings(1500);
        ValidationResponse validationResponse=new ValidationResponse();
        validationResponse.setEmailValidity(true);
        validationResponse.setIncomeValidity(false);
        validationResponse.setNameValidity(true);
        validationResponse.setMobileNumberValidity(true);
        assertEquals(validationResponse,validateService.validateCustomer(customerDetails));
    }
}