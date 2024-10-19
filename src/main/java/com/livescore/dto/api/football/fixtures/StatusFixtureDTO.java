package com.livescore.dto.api.football.fixtures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusFixtureDTO {

    @JsonProperty("long")
    private String longx;

    @JsonProperty("short")
    private String shortx;
    private int elapsed;
    private int extra;
}
