package com.example.mailservice.service;

import com.example.mailservice.dto.MailRequestDTO;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MailServiceImp implements MailService{
    @Autowired private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    @Override
    public String sendSimpleMessage(MailRequestDTO mailDto) {
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(mailDto.getRecipients());
            mailMessage.setText(mailDto.getContent());
            mailMessage.setSubject(mailDto.getTitle());

            javaMailSender.send(mailMessage);
            return "E-mail has been sent!";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Fail to send e-mail!";
        }
    }
}
