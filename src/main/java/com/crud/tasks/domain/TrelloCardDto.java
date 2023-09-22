package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrelloCardDto {

    private String name;
    private String description;
    private String pos;
    private String listId;

    public TrelloCardDto(String name, String description, String pos, String listId) {

        this.name = name;
        this.description = description;
        this.pos = pos;
        this.listId = listId;
    }

}
