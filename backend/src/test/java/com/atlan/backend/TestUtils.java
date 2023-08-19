package com.atlan.backend;

import com.atlan.backend.entity.CustomerDetails;
import com.atlan.backend.entity.MessageTemplate;
import com.atlan.backend.entity.SheetResponsePojo;
import com.atlan.backend.entity.SlangRequest;

public class TestUtils {
    public CustomerDetails getCustomerDetails(){
        CustomerDetails customerDetails=new CustomerDetails();
        customerDetails.setId("id");
        customerDetails.setName("Name");
        customerDetails.setEmail("email@email.com");
        customerDetails.setMonthlyIncome(1000);
        customerDetails.setMonthlySavings(500);
        customerDetails.setMobileNumber("xxxxxxxxxx");
        return customerDetails;
    }
    public SheetResponsePojo getSheetResponsePojo(){
        SheetResponsePojo sheetResponsePojo=new SheetResponsePojo();
        sheetResponsePojo.setSpreadSheetId("id");
        sheetResponsePojo.setSpreadSheetUrl("url");
        return sheetResponsePojo;
    }
    public SlangRequest getSlangRequest(){
        SlangRequest slangRequest=new SlangRequest();
        slangRequest.setToLang("en");
        slangRequest.setFromLang("hi");
        slangRequest.setText("what");
        return slangRequest;
    }
    public MessageTemplate getMessageTemplate(){
        MessageTemplate messageTemplate=new MessageTemplate();
        messageTemplate.setMobileNumber("xxxxxxxxxx");
        messageTemplate.setMessgeBody("body");
        return messageTemplate;
    }
}
