package com.livescore.service;

import com.livescore.dto.response.TopLeagueResponse;

import java.util.List;

public interface LeagueInfoService {

    void createLeagueInfo();

    List<TopLeagueResponse> getTopLeagues();
}
