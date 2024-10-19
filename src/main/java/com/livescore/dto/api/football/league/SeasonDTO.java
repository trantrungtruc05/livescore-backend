package com.livescore.dto.api.football.league;

import lombok.Data;

@Data
public class SeasonDTO {

    private int year;
    private String start;
    private String end;
    private boolean current;
    private CoverageDTO coverage;
}
