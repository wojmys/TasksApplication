package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyConfig {

    @Value("${Company}")
    private String companyName;

    @Value("example@gmail.com")
    private String email;

    @Value("123")
    private String phone;
}