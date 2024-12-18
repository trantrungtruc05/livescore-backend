package com.livescore.integration;

import com.livescore.constants.CommonConstant;
import com.livescore.dto.api.football.country.CountryDTO;
import com.livescore.dto.api.football.fixtures.FixtureResultDTO;
import com.livescore.dto.api.football.league.LeagueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@FeignClient(url = "${api.football.base-url}", name = "${api.football.name}", configuration = ApiFootballServiceConfiguration.class)
public interface ApiFootballServiceClient {

    @GetMapping(path = CommonConstant.API_FOOTBALL_LEAGUES)
    LeagueDTO getLeagues();

    @GetMapping(path = CommonConstant.API_FOOTBALL_COUNTRIES)
    CountryDTO getCountries();

    @GetMapping(path = CommonConstant.API_FOOTBALL_FIXTURES)
    FixtureResultDTO getFixtures(@RequestParam(value = "season", required = false) Integer season, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "status", required = false) String status, @RequestParam(value = "live", required = false) String live, @RequestParam("timezone") String timezone);
}
