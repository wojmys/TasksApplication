package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCardDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("badges")
    private Badges badges;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUrl;

    public CreatedTrelloCardDto(String id, String name, String shortUrl) {

        this.id = id;
        this.name = name;
        this.shortUrl = shortUrl;
    }
}