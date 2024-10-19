package com.livescore.dto.api.football.league;

import lombok.Data;

@Data
public class FixturesDTO {

    private boolean events;
    private boolean lineups;
    private boolean statistics_fixtures;
    private boolean statistics_players;
}
