package com.atlan.backend.controller;

import com.atlan.backend.entity.CustomerDetails;
import com.atlan.backend.entity.MessageTemplate;
import com.atlan.backend.entity.ValidationResponse;
import com.atlan.backend.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {
    @Autowired
    SmsService smsService;
    //Route for sending SMS
    @PostMapping("/sendSMS")
    public void addCustomer(@RequestBody MessageTemplate messageTemplate){
        smsService.sendMessage(messageTemplate);
    }
}
