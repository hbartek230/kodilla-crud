package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> funcionality = new ArrayList<>();
        funcionality.add("You can manage your tasks");
        funcionality.add("Provides connection with Trello Application");
        funcionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", "Test mail");
        context.setVariable("tasks_url", "http://localhost:8080/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", funcionality);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", message.substring(0, 19));
        context.setVariable("goodbye", "Thank youfor contact");
        context.setVariable("company_data", adminConfig.getCompanyName() + "/n" +
                adminConfig.getCompanyAddressStreet() + " " +
                adminConfig.getCompanyAddressNumber());
        context.setVariable("company_email", adminConfig.getAdminMail());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyEmailMessage(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8080/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", message.substring(0, 19));
        context.setVariable("goodbye", "Thank you for contact");
        context.setVariable("company_data", adminConfig.getCompanyName() + "/n" +
                adminConfig.getCompanyAddressStreet() + " " +
                adminConfig.getCompanyAddressNumber());
        context.setVariable("company_email", adminConfig.getAdminMail());
        return templateEngine.process("mail/created-daily-mail", context);
    }
}
