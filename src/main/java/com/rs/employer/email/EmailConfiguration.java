//package com.rs.employer.email;
//
//import jakarta.mail.SendFailedException;
//import jakarta.validation.constraints.Email;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class EmailConfiguration {
//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//       try {
//           mailSender.setHost("smtp.gmail.com");
//           mailSender.setUsername(System.getenv("EMAIL_USERNAME"));
//           mailSender.setPassword(System.getenv("EMAIL_PASSWORD"));
//           Properties props = mailSender.getJavaMailProperties();
//           props.put("mail.transport.protocol", "smtp");
//           props.put("mail.smtp.auth", "true");
//           props.put("mail.smtp.starttls.enable", "true");
//           props.put("mail.debug", "true");
//       }catch (Exception e) {
//           e.printStackTrace();
//       }
//    return mailSender;
//
//    }
//}
