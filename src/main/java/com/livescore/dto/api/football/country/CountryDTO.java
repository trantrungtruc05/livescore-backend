package com.livescore.dto.api.football.country;

import com.livescore.dto.api.football.AbstractApiFootballDTO;
import lombok.Data;

import java.util.List;

@Data
public class CountryDTO extends AbstractApiFootballDTO {

    private List<CountryInfoDTO> response;

}
