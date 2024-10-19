package com.livescore.dto.api.football.league;

import lombok.Data;

import java.util.List;

@Data
public class LeagueInfoDTO {

    private LeagueDetailDTO league;
    private CountryDTO country;
    private List<SeasonDTO> seasons;
}
