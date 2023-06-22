package com.example.mailservice.dto;

import java.util.ArrayList;
import java.util.List;

public class MailRequestDTO {
    private String title;
    private String[] recipients;
    private String content;

    public MailRequestDTO(){
    }

    public MailRequestDTO(String title, String[] recipients, String content) {
        this.title = title;
        this.recipients = recipients;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
