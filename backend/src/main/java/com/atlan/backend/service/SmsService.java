package com.atlan.backend.service;

import com.atlan.backend.entity.MessageTemplate;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    public static final String ACCOUNT_SID = "ACf5b75a78be2a76a4423014ae4745a5d6";
    public static final String AUTH_TOKEN = "d86a8f4924aa09a51895a9002734b9f6";
    public void sendMessage(MessageTemplate messageTemplate) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                        new com.twilio.type.PhoneNumber(messageTemplate.getMobileNumber()),
                        new com.twilio.type.PhoneNumber("+13345083467"),
                        messageTemplate.getMessgeBody())
                .create();
    }
}
