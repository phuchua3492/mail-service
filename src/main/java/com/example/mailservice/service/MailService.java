package com.example.mailservice.service;

import com.example.mailservice.dto.MailRequestDTO;

public interface MailService {
    String sendSimpleMessage(MailRequestDTO mailDto);
}
