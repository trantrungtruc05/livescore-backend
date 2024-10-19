package com.livescore.dto.api.football.league;

import lombok.Data;

@Data
public class CoverageDTO {

    private FixturesDTO fixtures;
    private boolean standings;
    private boolean players;
    private boolean top_scorers;
    private boolean top_assists;
    private boolean top_cards;
    private boolean injuries;
    private boolean predictions;
    private boolean odds;
}
