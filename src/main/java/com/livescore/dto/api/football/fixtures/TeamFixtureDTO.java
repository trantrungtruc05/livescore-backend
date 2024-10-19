package com.livescore.dto.api.football.fixtures;

import lombok.Data;

@Data
public class TeamFixtureDTO {

    private TeamDetailDTO home;
    private TeamDetailDTO away;
}
