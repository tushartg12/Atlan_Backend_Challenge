package com.atlan.backend.service;

import com.atlan.backend.entity.CustomerDetails;
import com.atlan.backend.entity.ValidationResponse;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidateService {
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static final String MOBILE_REGEX = "(0/91)?[6-9][0-9]{9}";
    public static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    public ValidationResponse validateCustomer(CustomerDetails customerDetails) {
        ValidationResponse validationResponse=new ValidationResponse();
        Matcher matcher=pattern.matcher(customerDetails.getEmail());
        validationResponse.setIncomeValidity(!(customerDetails.getMonthlyIncome() < customerDetails.getMonthlySavings()));
        validationResponse.setMobileNumberValidity(customerDetails.getMobileNumber().matches(MOBILE_REGEX));
        validationResponse.setEmailValidity(matcher.matches());;
        return validationResponse;
    }
}
