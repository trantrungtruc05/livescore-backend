package com.livescore.dto.api.football.fixtures;

import lombok.Data;

@Data
public class FixtureDTO {

    private FixtureDetailDTO fixture;
    private LeagueFixtureDTO league;
    private TeamFixtureDTO teams;
    private GoalDTO goals;
    private ScoreDTO score;

}
