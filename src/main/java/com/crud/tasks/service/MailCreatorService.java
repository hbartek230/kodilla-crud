package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", "Test mail");
        context.setVariable("tasks_url", "http://localhost:8080/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", message.substring(0, 19));
        context.setVariable("goodbye", "Thank youfor contact");
        context.setVariable("company_data", adminConfig.getCompanyName() + "/n" +
                adminConfig.getCompanyAddressStreet() + " " +
                adminConfig.getCompanyAddressNumber());
        context.setVariable("company_email", adminConfig.getAdminMail());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
