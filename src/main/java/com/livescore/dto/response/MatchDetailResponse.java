package com.livescore.dto.response;

import com.livescore.dto.api.football.fixtures.*;
import lombok.Data;

@Data
public class MatchDetailResponse {

    private FixtureDetailDTO fixture;

    private TeamFixtureDTO team;

    private GoalDTO goals;

    private ScoreDTO score;
}
