package com.atlan.backend.controller;

import com.atlan.backend.utils.TestUtils;
import com.atlan.backend.entity.SlangRequest;
import com.atlan.backend.service.SlangService;
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
class SlangControllerTest {
    @Mock
    SlangService slangService;
    @InjectMocks
    TestUtils testUtils;
    @InjectMocks SlangController slangController;
    @Test
    @Description("Happy Path")
    void findSlang_Test() throws Exception {
        SlangRequest slangRequest=testUtils.getSlangRequest();
        when(slangService.findSlang(slangRequest.getFromLang(),slangRequest.getToLang(),slangRequest.getText())).thenReturn("क्या");
        assertEquals(HttpStatus.OK,slangController.findSlang(slangRequest).getStatusCode());
        verify(slangService).findSlang(slangRequest.getFromLang(),slangRequest.getToLang(),slangRequest.getText());
    }
}