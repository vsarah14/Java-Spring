package com.example.CarRenting.business.observer;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class ObserverSend implements IObserverSend{
    @Override
    public void sendEmail(int i, String s) {

        System.out.println(s);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("assignmentSDUTCN@gmail.com");
        mailSender.setPassword("gaftiocgjnvurqvb");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth","true");
        properties.setProperty("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        mailSender.setJavaMailProperties(properties);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("assignmentSDUTCN@gmail.com");
        message.setTo("assignmentSDUTCN@gmail.com");

        if(i==1){
            message.setSubject("REGISTERED SUCCESSFULLY");
        }

        message.setText("User: " + s + " registered successfully.");
        mailSender.send(message);


    }
}
