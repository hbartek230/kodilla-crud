package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.name}")
    private String adminName;

    @Value("@{info.company.name")
    private String companyName;

    @Value("${info.app.administrator.address.street}")
    private String companyAddressStreet;

    @Value("${info.app.administrator.address.number}")
    private String companyAddressNumber;

    @Value("${info.app.administrator.email}")
    private String adminMail;
}
