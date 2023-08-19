package com.atlan.backend.controller;

import com.atlan.backend.entity.SlangRequest;
import com.atlan.backend.service.SlangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SlangController {
    @Autowired
    SlangService slangService;
    //Route for give slang words
    @PostMapping("/giveSlang")
    public ResponseEntity<String> findSlang(@RequestBody SlangRequest slangRequest) throws Exception {
        return new ResponseEntity<>(slangService.findSlang(slangRequest.getFromLang(), slangRequest.getToLang(), slangRequest.getText()), HttpStatus.OK);
    }
}
