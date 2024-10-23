package com.rs.employer.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@pmapi.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText("This is testing email");
        javaMailSender.send(message);
    }
}
