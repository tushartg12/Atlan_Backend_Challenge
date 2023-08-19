package com.atlan.backend.controller;

import com.atlan.backend.TestUtils;
import com.atlan.backend.service.SmsService;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SmsControllerTest {
    @Mock
    SmsService smsService;
    @InjectMocks SmsController smsController;
    @InjectMocks
    TestUtils testUtils;
    @Test
    @Description("Happy Path")
    void sendSMS_Test() {
        smsController.sendSMS(testUtils.getMessageTemplate());
        verify(smsService).sendMessage(testUtils.getMessageTemplate());
    }
}