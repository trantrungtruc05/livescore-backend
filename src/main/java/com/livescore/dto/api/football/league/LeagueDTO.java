package com.livescore.dto.api.football.league;

import com.livescore.dto.api.football.AbstractApiFootballDTO;
import lombok.Data;

import java.util.List;

@Data
public class LeagueDTO extends AbstractApiFootballDTO {

    private List<LeagueInfoDTO> response;
}
