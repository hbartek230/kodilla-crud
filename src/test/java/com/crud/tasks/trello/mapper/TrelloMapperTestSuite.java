package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void trelloMapperTest() {
        // given
        List<TrelloList> trelloLists = Collections.singletonList(new TrelloList("1", "name1", true)
        );
        List<TrelloBoard> trelloBoardList = Collections.singletonList(new TrelloBoard("1", "nameBoard1", trelloLists)
        );
        TrelloCard trelloCard = new TrelloCard("cardName1", "cardDescription1", "1", "1234");
        // when
        List<TrelloBoardDto> testedBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        List<TrelloListDto> testedTrelloListDto = trelloMapper.mapToListDto(trelloLists);
        TrelloCardDto testedTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // then
        assertEquals(testedBoardDtoList.get(0).getId(), trelloBoardList.get(0).getId());
        assertEquals(testedBoardDtoList.get(0).getName(), trelloBoardList.get(0).getName());
        assertEquals(testedTrelloListDto.get(0).getId(), trelloLists.get(0).getId());
        assertEquals(testedTrelloListDto.get(0).getName(), trelloLists.get(0).getName());
        assertEquals(testedTrelloListDto.get(0).isClosed(), trelloLists.get(0).isClosed());
        assertEquals(testedTrelloCardDto.getName(), trelloCard.getName());
        assertEquals(testedTrelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(testedTrelloCardDto.getPos(), trelloCard.getPos());
        assertEquals(testedTrelloCardDto.getListId(), trelloCard.getListId());
    }
}
