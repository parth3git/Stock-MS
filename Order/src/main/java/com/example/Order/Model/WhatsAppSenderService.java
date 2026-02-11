package com.example.Order.Model;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppSenderService {
    @Value("${twilio.whatsapp.from}")
    private String whatsappFrom;

    @Value("${admin.whatsapp}")
    private String adminWhatsapp;

    public void sendWhatsAppMs(String to,String body) {
        Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(whatsappFrom),
                body
        ).create();
    }

    //Admin
    public void sendAdminAlert(String msg) {
        Message.creator(
                new PhoneNumber(adminWhatsapp),
                new PhoneNumber(whatsappFrom),
                msg
        ).create();
    }
}