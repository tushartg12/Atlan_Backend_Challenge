package com.atlan.backend.controller;

import com.atlan.backend.TestUtils;
import com.atlan.backend.entity.ValidationResponse;
import com.atlan.backend.service.ValidateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ValidateControllerTest {
    @Mock
    ValidateService validateService;
    @InjectMocks
    ValidateController validateController;
    @InjectMocks
    TestUtils testUtils;
    @Test
    void validateCustomer() {
        when(validateService.validateCustomer(testUtils.getCustomerDetails())).thenReturn(new ValidationResponse());
        assertEquals(HttpStatus.OK,validateController.validateCustomer(testUtils.getCustomerDetails()).getStatusCode());
    }
}