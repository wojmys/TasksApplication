package com.crud.tasks.repository;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
@SpringBootApplication
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void shouldSendEmail() {

    //given
        Mail mail =  Mail.builder()
                .mailTo("test@test.com")
                .message("text")
                .subject("subject")
                .toCc("")
                .build();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);

    }
}