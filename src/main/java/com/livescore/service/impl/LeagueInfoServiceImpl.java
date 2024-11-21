package com.livescore.service.impl;

import com.livescore.dto.api.football.country.CountryDTO;
import com.livescore.dto.api.football.country.CountryInfoDTO;
import com.livescore.dto.api.football.league.LeagueDTO;
import com.livescore.dto.api.football.league.LeagueInfoDTO;
import com.livescore.dto.response.TopLeagueResponse;
import com.livescore.entity.Country;
import com.livescore.entity.LeagueInfo;
import com.livescore.integration.ApiFootballServiceClient;
import com.livescore.repository.CountryRepository;
import com.livescore.repository.LeagueInfoRepository;
import com.livescore.service.LeagueInfoService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LeagueInfoServiceImpl implements LeagueInfoService {

    @Value("${league.priority-config}")
    private String prorityConfig;

    @Autowired
    private LeagueInfoRepository leagueInfoRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ApiFootballServiceClient apiFootballServiceClient;

    @Override
    @Transactional
    public void createLeagueInfo() {

        LeagueDTO leagueDTO = apiFootballServiceClient.getLeagues();

        int size = leagueDTO.getResponse().size();

        log.info("START get info leagues ...");
        leagueInfoRepository.deleteAll();
        for(LeagueInfoDTO responseDTO : leagueDTO.getResponse()){

            log.info("GET INFO LEAGUE remain : {} ", size);
            log.info("GET INFO LEAGUE with Country: {}", responseDTO.getCountry().getName());
            log.info("GET INFO LEAGUE with League: {}", responseDTO.getLeague().getName());



            LeagueInfo leagueInfo = new LeagueInfo();
            leagueInfo.setId(responseDTO.getLeague().getId());
            leagueInfo.setName(responseDTO.getLeague().getName());
            leagueInfo.setType(responseDTO.getLeague().getType());
            leagueInfo.setLogo(responseDTO.getLeague().getLogo());
            leagueInfo.setCountry(responseDTO.getCountry().getName());
            leagueInfo.setCountryCode(responseDTO.getCountry().getCode());
            leagueInfo.setCountryFlag(responseDTO.getCountry().getFlag());

            leagueInfoRepository.save(leagueInfo);
            size--;
        }

        log.info("START get info country ...");
        countryRepository.deleteAll();

        CountryDTO countryDTO = apiFootballServiceClient.getCountries();
        for(CountryInfoDTO responseDTO : countryDTO.getResponse()){
            Country country = new Country(responseDTO.getName(), responseDTO.getCode(), responseDTO.getFlag());
            countryRepository.save(country);
        }


        // update priority league
        for(String s : prorityConfig.split(";")){
            leagueInfoRepository.updatePriorityLeagueInfo(Integer.parseInt(s.split("-")[1]), Integer.parseInt(s.split("-")[0]));
        }



    }

    @Override
    public List<TopLeagueResponse> getTopLeagues() {
        List<LeagueInfo> leagueInfoList = leagueInfoRepository.findAllByPriorityNotNullOrderByPriority();

        List<TopLeagueResponse> topLeagueResponseList = new ArrayList<>();

        for(LeagueInfo leagueInfo : leagueInfoList){
            TopLeagueResponse topLeagueResponse = getTopLeagueResponse(leagueInfo);
            topLeagueResponseList.add(topLeagueResponse);
        }

        return topLeagueResponseList;
    }

    private static TopLeagueResponse getTopLeagueResponse(LeagueInfo leagueInfo) {
        TopLeagueResponse topLeagueResponse = new TopLeagueResponse();
        topLeagueResponse.setId(leagueInfo.getId());
        topLeagueResponse.setName(leagueInfo.getName());
        topLeagueResponse.setType(leagueInfo.getType());
        topLeagueResponse.setLogo(leagueInfo.getLogo());
        topLeagueResponse.setCountry(leagueInfo.getCountry());
        topLeagueResponse.setCountryCode(leagueInfo.getCountryCode());
        topLeagueResponse.setCountryFlag(leagueInfo.getCountryFlag());
        return topLeagueResponse;
    }
}
