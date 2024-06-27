package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.model.enums.ReservationStatus;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MessageService {
    private final JavaMailSender javaMailSender;

    @Async
    public void loginNotification( String email, String firstName ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(email);
        messageHelper.setSubject("[LOGIN NOTIFICATION]");
        String message = String.format("Dear %s, \n You have successfully logged-in. Your logged in email: %s", firstName, email);
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());


    }

    @Async
    public void registrationNotification( String email, String firstName) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setSubject("[Registration Successful!]");
        messageHelper.setTo(email);
        String message = String.format("Dear %s, \n You have successfully registered. Your registered email: %s", firstName, email);
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());


    }
    @Async
    public void reservationNotification(String firstName, String email, String reservationNumber, String status) throws MessagingException{

        String endpointUrl = "http://localhost:8085/api/reservation/get-reservation/" + reservationNumber;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        String message = String.format("Dear %s, \n A You have successfully made a reservation with details: Reservation Number: %s\n " +
                "Email Address: %s\n Status: %s\n " +
                "please use the link below to CANCEL or PAY for your reservation\n " +
                "<a href=\"%s\">Manage Reservation</a> ", firstName, email, reservationNumber, status, endpointUrl);
        mimeMessageHelper.setSubject("[RESERVATION NOTIFICATION]");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setText(message, true);

        javaMailSender.send(mimeMessageHelper.getMimeMessage());


    }

    @Async
    public void adminRegistrationNotification( String email, String firstName) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setSubject("[[ADMIN UPGRADE]]!");
        messageHelper.setTo(email);
        String message = String.format("Dear %s, \n You have successfully been made an ADMIN. Your registered email: %s", firstName, email);
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());


    }



}
