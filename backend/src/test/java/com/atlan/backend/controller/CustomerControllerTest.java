package com.atlan.backend.controller;

import com.atlan.backend.utils.TestUtils;
import com.atlan.backend.entity.ValidationResponse;
import com.atlan.backend.service.CustomerService;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.List.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    CustomerService customerService;
    @InjectMocks
    TestUtils testUtils;
    @InjectMocks
    CustomerController customerController;

    @Test
    @Description("Happy path")
    void getAllCustomers_Test() {
        when(customerService.getAllCustomers()).thenReturn(List.of(testUtils.getCustomerDetails()));
        assertEquals(HttpStatus.OK,customerController.getAllCustomers().getStatusCode());
        verify(customerService).getAllCustomers();
    }

    @Test
    @Description("Happy Path")
    void addCustomer_Test1() {
        when(customerService.addCustomer(testUtils.getCustomerDetails())).thenReturn("Customer added successfully");
        assertEquals(HttpStatus.CREATED,customerController.addCustomer(testUtils.getCustomerDetails()).getStatusCode());
        verify(customerService).addCustomer(testUtils.getCustomerDetails());
    }
    @Test
    @Description("When some invalid data sent")
    void addCustomer_Test2() {
        when(customerService.addCustomer(testUtils.getCustomerDetails())).thenReturn(new ValidationResponse());
        assertEquals(HttpStatus.NOT_ACCEPTABLE,customerController.addCustomer(testUtils.getCustomerDetails()).getStatusCode());
        verify(customerService).addCustomer(testUtils.getCustomerDetails());
    }
}