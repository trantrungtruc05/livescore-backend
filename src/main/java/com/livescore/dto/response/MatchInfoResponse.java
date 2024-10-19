package com.livescore.dto.response;

import com.livescore.dto.api.football.fixtures.LeagueFixtureDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchInfoResponse {

    private LeagueFixtureDTO league;

    private List<MatchDetailResponse> matches;

    public MatchInfoResponse(LeagueFixtureDTO league) {
        this.league = league;
        this.matches = new ArrayList<>();
    }

    public void addMatches(MatchDetailResponse matchDetailResponse) {
        this.matches.add(matchDetailResponse);
    }


}
