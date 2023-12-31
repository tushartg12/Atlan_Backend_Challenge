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
        char[] chars = customerDetails.getName().toCharArray();
        //Validation of name - No digits are allowed
        for(char c : chars){
            if(Character.isDigit(c)){
                validationResponse.setNameValidity(false);
                break;
            }
            else{
                validationResponse.setNameValidity(true);
            }
        }
        //Validation of Income - Savings should not be greater tha income
        validationResponse.setIncomeValidity(!(customerDetails.getMonthlyIncome() < customerDetails.getMonthlySavings()));
        //Validation of Mobile Number - starts with 6-9 and must be of 10 digits
        validationResponse.setMobileNumberValidity(customerDetails.getMobileNumber().matches(MOBILE_REGEX));
        //Validation of Email - should be of correct email syntax
        validationResponse.setEmailValidity(matcher.matches());
        return validationResponse;
    }
}
