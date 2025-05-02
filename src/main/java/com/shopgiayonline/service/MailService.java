package com.shopgiayonline.service;

import com.shopgiayonline.dto.DataMailRequest;

import jakarta.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailRequest dataMail, String templateName) throws MessagingException;

    void sendHtmlMailOTP(DataMailRequest dataMail, String templateName) throws MessagingException;
}
