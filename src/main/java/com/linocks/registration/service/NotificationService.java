package com.linocks.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    JavaMailSender javaMailSender;

    @Async("threadPoolTaskExecutor")
    public void sendRegistrationEmail(String toEmail){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject("Registration Successful");
        msg.setText("Your registration has been received successfully");
        javaMailSender.send(msg);
    }
}

