package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;

    Mail mail = Mail.builder()
            .mailTo("aaa@gmail.com")
            .subject("sb")
            .message("message")
            .build();


    public void send(final Mail mail) {
        log.info("Starting email preparation...");

        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("Email sent");
        } catch (MailException e) {
            log.error("Failed to sent. " + e.getMessage(), e);
        }
    }

        SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        Optional<String> ccCheck = Optional.of(mail.getToCc());

        if (ccCheck.isPresent()) {
            mailMessage.setCc(mailMessage.getCc());
        }
        return mailMessage;
    }


}