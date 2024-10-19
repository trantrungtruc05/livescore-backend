package com.livescore.dto.api.football.fixtures;

import lombok.Data;

@Data
public class FixtureDetailDTO {

    private int id;
    private String referee;
    private String timezone;
    private String date;
    private int timestamp;
    private PeriodFixtureDTO periods;
    private VenueFixtureDTO venue;
    private StatusFixtureDTO status;
}
