package com.alkemy.disney.disney.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private final JavaMailSender javaMailSender;


    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendWelcomeMailTo (String username){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String subject = "Welcome!";
        String message = "Here is a welcome back message for you...";
        //TODO:habilitar envio SMTP en el correo de salida
        String from = "fakesenderaccount@fake.com";
        String to = username;

        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }

}
