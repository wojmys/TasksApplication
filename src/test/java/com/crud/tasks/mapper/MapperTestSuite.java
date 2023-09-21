package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MapperTestSuite {

    @Autowired
    TrelloMapper mapper;

    @Test
    void mapToBoardsDtoTest() {
        //given
        TrelloBoard toDo = new TrelloBoard("1", "toDo", new ArrayList<>());
        TrelloBoard inProgress = new TrelloBoard("2", "inProgress", new ArrayList<>());
        List<TrelloBoard> board = new ArrayList<>();
        board.add(toDo);
        board.add(inProgress);
        //when
        List<TrelloBoardDto> boardDto;
        boardDto = mapper.mapToBoardsDto(board);
        //then
        Assertions.assertEquals(2, boardDto.size());
        Assertions.assertEquals("toDo", boardDto.get(0).getName());
        Assertions.assertEquals("inProgress", boardDto.get(1).getName());
    }

    @Test
    void mapToBoardsTest() {
        //given
        TrelloBoardDto toDo = new TrelloBoardDto("1", "toDo", new ArrayList<>());
        TrelloBoardDto inProgress = new TrelloBoardDto("2", "inProgress", new ArrayList<>());
        List<TrelloBoardDto> boardDto = new ArrayList<>();
        boardDto.add(toDo);
        boardDto.add(inProgress);
        //when
        List<TrelloBoard> board = new ArrayList<>();
        List<TrelloBoard> emptyBoard = board;
        board = mapper.mapToBoards(boardDto);
        //then
        Assertions.assertTrue(emptyBoard.isEmpty());
        Assertions.assertFalse(board.isEmpty());
        Assertions.assertEquals(2, board.size());
        Assertions.assertEquals("toDo", board.get(0).getName());
        Assertions.assertEquals("inProgress", board.get(1).getName());
    }


    @Test
    void mapToListTest() {
        //given
        TrelloListDto toDoDto = new TrelloListDto("1", "toDo", false);
        TrelloListDto inProgressDto = new TrelloListDto("2", "inProgress", false);
        TrelloListDto doneDto = new TrelloListDto("3", "done", true);
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(toDoDto);
        listDto.add(inProgressDto);
        listDto.add(doneDto);
        //when
        List<TrelloList> list = mapper.mapToList(listDto);
        //then
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("done", list.get(2).getName());
    }

    @Test
    void mapToListDtoTest() {
        //given
        TrelloList toDo = new TrelloList("1", "toDo", false);
        TrelloList inProgress = new TrelloList("2", "inProgress", false);
        TrelloList done = new TrelloList("3", "done", true);
        List<TrelloList> list = new ArrayList<>();
        list.add(toDo);
        list.add(inProgress);
        list.add(done);
        //when
        List<TrelloListDto> listDto = mapper.mapToListDto(list);
        //then
        Assertions.assertFalse(listDto.isEmpty());
        Assertions.assertEquals(3, listDto.size());
        Assertions.assertEquals("done", listDto.get(2).getName());
    }

    @Test
    void mapToMapToCardTest() {
        //given
        TrelloCardDto cardDto = new TrelloCardDto("name", "description", "pos", "listId");
        //when
        TrelloCard card = mapper.mapToCard(cardDto);
        //then
        Assertions.assertEquals("name", card.getName());
        Assertions.assertEquals("description", card.getDescription());
        Assertions.assertEquals("pos", card.getPos());
        Assertions.assertEquals("listId", card.getListId());
    }

    @Test
    void mapToMapToCardDtoTest() {
        //given
        TrelloCard card = new TrelloCard("name", "description", "pos", "listId");
        //when
        TrelloCardDto cardDto = mapper.mapToCardDto(card);
        //then
        Assertions.assertEquals("name", cardDto.getName());
        Assertions.assertEquals("description", cardDto.getDescription());
        Assertions.assertEquals("pos", cardDto.getPos());
        Assertions.assertEquals("listId", cardDto.getListId());
    }


}
