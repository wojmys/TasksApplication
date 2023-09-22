package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreatedTrelloCardDtoTestSuite {

    @Test
    void CreatedTrelloCardDtoTestSuite() {

        //given
        Trello trello = new Trello(1,1);
        AttachmentByType attachmentByType = new AttachmentByType(trello);
        Badges badges = new Badges(2,attachmentByType);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("12CY",badges, "name", "url");
        //when&then
        assertEquals("12CY", createdTrelloCardDto.getId());
        assertEquals("name", createdTrelloCardDto.getName());
        assertEquals("url", createdTrelloCardDto.getShortUrl());
        assertEquals(2,createdTrelloCardDto.getBadges().getVotes());
        assertEquals(1,createdTrelloCardDto.getBadges().getAttachmentsByType().getTrello().getCard());
        assertEquals(1,createdTrelloCardDto.getBadges().getAttachmentsByType().getTrello().getBoard());
    }
}
