package com.crud.tasks.trello.controller;

import com.crud.tasks.controller.TrelloController;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception {
        // given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();

    }

}