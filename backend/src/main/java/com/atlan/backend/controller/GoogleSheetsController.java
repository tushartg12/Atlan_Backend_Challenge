package com.atlan.backend.controller;

import com.atlan.backend.entity.SheetResponsePojo;
import com.atlan.backend.service.GoogleSheetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleSheetsController {
    @Autowired
    GoogleSheetsService googleSheetsService;
    @GetMapping("/createSheet")
    public ResponseEntity<SheetResponsePojo> createSheet() throws Exception {
        return new ResponseEntity<>(googleSheetsService.createSheet(), HttpStatus.CREATED);
    }
}
