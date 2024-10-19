package com.livescore.dto.api.football.fixtures;

import com.livescore.dto.api.football.AbstractApiFootballDTO;
import lombok.Data;

import java.util.List;

@Data
public class FixtureResultDTO extends AbstractApiFootballDTO {

    private List<FixtureDTO> response;
}
