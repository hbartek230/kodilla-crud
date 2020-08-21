package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New Trello card";

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCard) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCard);
        ofNullable(newCard).ifPresent(card ->
                emailService.send(new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "New card " + trelloCard.getName() + " has been created on your Trello board",
                        "test@testowy.com"))
        );

        return newCard;
    }
}
