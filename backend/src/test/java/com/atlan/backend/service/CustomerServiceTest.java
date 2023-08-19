package com.atlan.backend.service;

import com.atlan.backend.utils.TestUtils;
import com.atlan.backend.entity.MessageTemplate;
import com.atlan.backend.entity.ValidationResponse;
import com.atlan.backend.repository.CustomerRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock ValidateService validateService;
    @Mock SmsService smsService;
    @InjectMocks CustomerService customerService;
    @InjectMocks
    TestUtils testUtils;
    @Test
    @Description("Happy Path")
    void getAllCustomers_Test() {
        when(customerRepository.findAll()).thenReturn(List.of(testUtils.getCustomerDetails()));
        assertEquals("Name",customerService.getAllCustomers().get(0).getName());
        verify(customerRepository).findAll();
    }

    @Test
    @Description("Happy Path")
    void addCustomer_Test1() {
        ValidationResponse validationResponse=new ValidationResponse();
        validationResponse.setNameValidity(true);
        validationResponse.setEmailValidity(true);
        validationResponse.setMobileNumberValidity(true);
        validationResponse.setIncomeValidity(true);
        when(validateService.validateCustomer(testUtils.getCustomerDetails())).thenReturn(validationResponse);
        when(customerRepository.save(testUtils.getCustomerDetails())).thenReturn(testUtils.getCustomerDetails());
        assertEquals("Customer added successfully",customerService.addCustomer(testUtils.getCustomerDetails()));
        verify(validateService).validateCustomer(testUtils.getCustomerDetails());
        verify(smsService).sendMessage(any(MessageTemplate.class));
        verify(customerRepository).save(testUtils.getCustomerDetails());
    }
    @Test
    @Description("Invalid Data")
    void addCustomer_Test2() {
        ValidationResponse validationResponse=new ValidationResponse();
        validationResponse.setNameValidity(true);
        validationResponse.setEmailValidity(false);
        validationResponse.setMobileNumberValidity(true);
        validationResponse.setIncomeValidity(true);
        when(validateService.validateCustomer(testUtils.getCustomerDetails())).thenReturn(validationResponse);
        assertEquals(validationResponse,customerService.addCustomer(testUtils.getCustomerDetails()));
        verify(validateService).validateCustomer(testUtils.getCustomerDetails());
    }
}