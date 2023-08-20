package com.atlan.backend.service;

import com.atlan.backend.Credentials.SmsServiceCredentials;
import com.atlan.backend.entity.MessageTemplate;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    public static final String ACCOUNT_SID = SmsServiceCredentials.ACCOUNT_SID;
    public static final String AUTH_TOKEN = SmsServiceCredentials.AUTH_TOKEN;
    public void sendMessage(MessageTemplate messageTemplate) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                        new com.twilio.type.PhoneNumber("+91"+messageTemplate.getMobileNumber()),
                        new com.twilio.type.PhoneNumber("+13345083467"),
                        messageTemplate.getMessgeBody())
                .create();
    }
}
