package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {

    @InjectMocks
    private TrelloValidator trelloValidator;

    private TrelloCard trelloCard;
    private List<TrelloBoard> trelloBoardList;

    @Before
    public void init() {
        trelloCard = new TrelloCard("test_name", "test_desc", "pos", "1234");
        trelloBoardList = Collections.singletonList(
                new TrelloBoard(
                        "1",
                        "test_name",
                        Collections.singletonList(new TrelloList("1", "test_name", true))));
    }

    @Test
    public void shouldValidateTrelloBoard() {
        // given

        // when
        List<TrelloBoard> expectedList = trelloValidator.validateTrelloBoards(trelloBoardList);

        // then
        assertEquals(1, expectedList.size());
    }
}
