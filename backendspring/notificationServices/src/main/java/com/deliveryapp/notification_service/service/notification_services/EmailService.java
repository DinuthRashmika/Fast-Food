package com.deliveryapp.notification_service.service.notification_services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    //NORMAL EMAIL
    public void sendEmail(String toEmail, String subject, String body){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sapunikasr@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        System.out.println("Email sent successfully to " + toEmail);

    }

    //EMAIL WITH ATTACHMENT
    public void sendEmailAttachments(String toEmail, String subject, String body) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("sapunikasr@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);

            helper.addAttachment("logo.jpg", new File("//Users//pamali.ranasinghe//Downloads//logo.jpg"));

            mailSender.send(message);
            System.out.println("Email sent successfully to " + toEmail);

        } catch (MessagingException e) {

            e.printStackTrace();
            System.out.println("Failed to send email to " + toEmail);
        }
    }

    //CUSTOMER EMAILS
    public void sendEmailToCustomer(String toEmail, String subject, String body, String orderId, String restaurantName, double total) throws IOException {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            if (toEmail == null || toEmail.isEmpty() || orderId == null || orderId.isEmpty() || restaurantName == null || restaurantName.isEmpty() || total <= 0) {
                toEmail = "pamalisr@gmail.com";  
                orderId = "";  
                restaurantName = "";
                total = 0.0;
            }

            helper.setFrom("sapunikasr@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);

            try(var inputStream = Objects.requireNonNull(EmailService.class.getResourceAsStream("/templates/htmltemplateCustomer.html")) ) {

                String template = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                template = template.replace("{{title}}", subject)
                                .replace("{{message}}", body)
                                .replace("{{orderId}}", orderId)
                                .replace("{{restaurantName}}", restaurantName)
                                .replace("{{total}}", String.valueOf(total));
                helper.setText(template, true);
            } 

            helper.addInline("logo.jpg", new File("//Users//pamali.ranasinghe//Documents//UNI//DS//Assignment 1//logo.jpg"));
            helper.addInline("ubereats.png", new File("//Users//pamali.ranasinghe/Documents//UNI//DS//Assignment 1//ubereats.png"));
            helper.addInline("ubereats2.png", new File("//Users//pamali.ranasinghe/Documents//UNI//DS//Assignment 1//ubereats2.png"));

            mailSender.send(message);
            System.out.println("Email sent successfully to " + toEmail);

        } catch (MessagingException e) {

            e.printStackTrace();
            System.out.println("Failed to send email to " + toEmail);
        }
    }


    //RESTAURENT EMAILS
    public void sendEmailToRestaurent(String toEmail, String subject, String body, String orderId, double total, List<String> orderedItems) throws IOException {

        if (toEmail == null || toEmail.isEmpty() || orderId == null || orderId.isEmpty() || orderedItems == null || orderedItems.isEmpty() || total <= 0) {
            toEmail = "pamalisr@gmail.com";  
            orderId = "";  
            orderedItems = new ArrayList<>();
            total = 0.0;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("sapunikasr@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);

            try(var inputStream = Objects.requireNonNull(EmailService.class.getResourceAsStream("/templates/htmltemplateRestaurent.html")) ) {

                String template = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                StringBuilder orderedItemsFormatted = new StringBuilder();
                for (String item : orderedItems) {
                    orderedItemsFormatted.append("- ").append(item).append("<br>");  // each item in a new line with HTML <br>
                }

                template = template.replace("{{title}}", subject)
                                .replace("{{message}}", body)
                                .replace("{{orderId}}", orderId)
                                .replace("{{total}}", String.valueOf(total))
                                .replace("{{orderedItems}}", orderedItemsFormatted.toString());
                helper.setText(template, true);
            } 

            helper.addInline("logo.jpg", new File("//Users//pamali.ranasinghe//Documents//UNI//DS//Assignment 1//logo.jpg"));
            helper.addInline("ubereats.png", new File("//Users//pamali.ranasinghe/Documents//UNI//DS//Assignment 1//ubereats.png"));
            helper.addInline("ubereats2.png", new File("//Users//pamali.ranasinghe/Documents//UNI//DS//Assignment 1//ubereats2.png"));

            mailSender.send(message);
            System.out.println("Email sent successfully to " + toEmail);

        } catch (MessagingException e) {

            e.printStackTrace();
            System.out.println("Failed to send email to " + toEmail);
        }
    }


    //DELIVERY EMAILS
    public void sendEmailToDeliveryPersonnal(String toEmail, String subject, String body, String orderId, String deliveryAddress, String restaurantName) throws IOException {

        if (toEmail == null || toEmail.isEmpty() || orderId == null || orderId.isEmpty() || deliveryAddress == null || deliveryAddress.isEmpty() || restaurantName == null || restaurantName.isEmpty()) {
            toEmail = "pamalisr@gmail.com";  
            orderId = "";  
            restaurantName = "";
            deliveryAddress = "";
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("sapunikasr@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);

            try(var inputStream = Objects.requireNonNull(EmailService.class.getResourceAsStream("/templates/htmltemplateDelivery.html")) ) {

                String template = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                template = template.replace("{{title}}", subject)
                                .replace("{{message}}", body)
                                .replace("{{orderId}}", orderId)
                                .replace("{{deliveryAddress}}", deliveryAddress)
                                .replace("{{restaurantName}}", restaurantName);
                helper.setText(template, true);
            } 

            helper.addInline("logo.jpg", new File("//Users//pamali.ranasinghe//Documents//UNI//DS//Assignment 1//logo.jpg"));
            helper.addInline("ubereats.png", new File("//Users//pamali.ranasinghe/Documents//UNI//DS//Assignment 1//ubereats.png"));
            helper.addInline("ubereats2.png", new File("//Users//pamali.ranasinghe/Documents//UNI//DS//Assignment 1//ubereats2.png"));

            mailSender.send(message);
            System.out.println("Email sent successfully to " + toEmail);

        } catch (MessagingException e) {

            e.printStackTrace();
            System.out.println("Failed to send email to " + toEmail);
        }
    }


    //ADMIN EMAILS
    public void sendEmailToAdmin(String toEmail, String subject, String body) throws IOException {

        if (toEmail == null || toEmail.isEmpty()) {
            toEmail = "pamalisr@gmail.com";  
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("sapunikasr@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);

            try(var inputStream = Objects.requireNonNull(EmailService.class.getResourceAsStream("/templates/htmltemplateAdmin.html")) ) {

                String template = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                template = template.replace("{{title}}", subject)
                                .replace("{{message}}", body);
                helper.setText(template, true);
            } 

            helper.addInline("logo.jpg", new File("//Users//pamali.ranasinghe//Documents//UNI//DS//Assignment 1//logo.jpg"));
            helper.addInline("ubereats.png", new File("//Users//pamali.ranasinghe/Documents//UNI//DS//Assignment 1//ubereats.png"));
            helper.addInline("ubereats2.png", new File("//Users//pamali.ranasinghe/Documents//UNI//DS//Assignment 1//ubereats2.png"));

            mailSender.send(message);
            System.out.println("Email sent successfully to " + toEmail);

        } catch (MessagingException e) {

            e.printStackTrace();
            System.out.println("Failed to send email to " + toEmail);
        }
    }

    
}
