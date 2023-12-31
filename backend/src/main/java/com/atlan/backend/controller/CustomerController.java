package com.atlan.backend.controller;

import com.atlan.backend.entity.CustomerDetails;
import com.atlan.backend.entity.ValidationResponse;
import com.atlan.backend.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    //Route for fetch all customer details
    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDetails>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }
    //Route for add customer
    @PostMapping("/addCustomer")
    public ResponseEntity<Object> addCustomer(@RequestBody @Valid CustomerDetails customerDetails){
        Object response = customerService.addCustomer(customerDetails);
        if(response instanceof ValidationResponse){
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        else{
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }
}
