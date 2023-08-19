package com.atlan.backend.controller;

import com.atlan.backend.utils.TestUtils;
import com.atlan.backend.service.GoogleSheetsService;
import jdk.jfr.Description;
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
class GoogleSheetsControllerTest {
    @Mock
    GoogleSheetsService googleSheetsService;
    @InjectMocks
    GoogleSheetsController googleSheetsController;
    @InjectMocks
    TestUtils testUtils;
    @Test
    @Description("Happy Path")
    void createSheet_Test() throws Exception {
        when(googleSheetsService.createSheet()).thenReturn(testUtils.getSheetResponsePojo());
        assertEquals(HttpStatus.CREATED,googleSheetsController.createSheet().getStatusCode());
        verify(googleSheetsService).createSheet();
    }
}