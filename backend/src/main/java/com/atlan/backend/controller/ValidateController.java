package com.atlan.backend.controller;

import com.atlan.backend.entity.CustomerDetails;
import com.atlan.backend.entity.SlangRequest;
import com.atlan.backend.entity.ValidationResponse;
import com.atlan.backend.service.ValidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateController {
    @Autowired
    ValidateService validateService;
    //Route for validating customer Details
    @PostMapping("/validateCustomer")
    public ResponseEntity<ValidationResponse> validateCustomer(@RequestBody @Valid CustomerDetails customerDetails){
        return new ResponseEntity<>(validateService.validateCustomer(customerDetails),HttpStatus.OK);
    }
}
