package com.example.mailservice.controller;

import com.example.mailservice.dto.MailRequestDTO;
import com.example.mailservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    @Autowired
    private MailService mailService;
    @PostMapping("/send")
    public String sendMail(@RequestBody MailRequestDTO mailDto){
        return mailService.sendSimpleMessage(mailDto);
    }
}
