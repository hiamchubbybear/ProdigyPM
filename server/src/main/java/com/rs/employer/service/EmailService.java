package com.rs.employer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendActivateToken(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@pmapi.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Your activate token is : " + content);
        System.out.println("Send email successful");
        javaMailSender.send(message);
    }
    public void sendResetPasswordLink(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@pmapi.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText("This is your link to reset your password : " + content);
        System.out.println("Send email successful");
        javaMailSender.send(message);
    }
}
