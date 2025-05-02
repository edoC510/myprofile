package com.shopgiayonline.core.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopgiayonline.common.util.Const;
import com.shopgiayonline.core.admin.model.request.AdminUserRequest;
import com.shopgiayonline.core.admin.model.request.OTPResquest;
import com.shopgiayonline.core.admin.service.ClientService;
import com.shopgiayonline.dto.DataMailRequest;
import com.shopgiayonline.service.MailService;

import jakarta.mail.MessagingException;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private MailService mailService;

    @Override
    public Boolean create(AdminUserRequest adminUserRequest) {
        try {
            DataMailRequest dataMail = new DataMailRequest();

            dataMail.setTo(adminUserRequest.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("ten", adminUserRequest.getName());
            props.put("userName", adminUserRequest.getUsername());
            props.put("password", adminUserRequest.getPassword());
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp) {
            exp.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean createOTP(OTPResquest otpRequest) {
        try {
            DataMailRequest dataMail = new DataMailRequest();

            dataMail.setTo(otpRequest.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_OTP.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("title", otpRequest.getTitle());
            props.put("ten", otpRequest.getName());
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_OTP_NAME.CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp) {
            exp.printStackTrace();
        }
        return false;
    }
}
