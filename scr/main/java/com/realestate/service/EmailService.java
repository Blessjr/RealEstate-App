package com.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Method to send general emails
    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@realestateapp.com");  // Change this to your actual "from" email
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    // Method to send a welcome email
    public void sendWelcomeEmail(String email) {
        String subject = "Welcome to RealEstate App";
        String body = "Thank you for registering with RealEstate App!";
        sendEmail(email, subject, body);
    }
}
