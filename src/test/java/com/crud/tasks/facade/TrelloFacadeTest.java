package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    void shouldFetchEmptyList() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(List.of());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        // Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }
    @Test
    void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {

            assertEquals("1", trelloBoardDto.getId());
            assertEquals("test", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("test_list", trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });

    }
    @Test
    void createCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_name", "test", "pos", "test_id");
        TrelloCard trelloCard = new TrelloCard("test_name", "test", "pos", "test_id");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id1", "test_name", "shortUrl");

        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto trelloFacadeCard = trelloFacade.createCard(trelloCardDto);
        //Then
        assertNotNull(trelloFacadeCard);
        assertEquals(createdTrelloCardDto, trelloFacadeCard);
    }
}
