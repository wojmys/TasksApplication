package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    @Autowired
    private MailCreatorService mailCreatorService;

    private final JavaMailSender javaMailSender;

    public void send(final Mail mail, String typeOfMail) {

        log.info("Starting email preparation...");
        try {
            MimeMessagePreparator mailMessage = createMimeMessage(mail, typeOfMail);
            javaMailSender.send(mailMessage);
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        Optional<String> toCc = Optional.ofNullable(mail.getToCc());
        if (toCc.isPresent()) {
            mailMessage.setCc(mail.getToCc());
            log.info("Copy sent.");
        }
        return mailMessage;
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, String typeOfMail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            if (typeOfMail.equals("card")) {
                messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
            } else if (typeOfMail.equals("daily")) {
                messageHelper.setText(mailCreatorService.buildDailySumaryEmail(mail.getMessage()), true);
            }
        };
    }
}