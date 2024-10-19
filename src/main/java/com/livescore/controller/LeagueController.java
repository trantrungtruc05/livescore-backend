package com.livescore.controller;

import com.livescore.constants.CommonConstant;
import com.livescore.dto.response.CoreApiResponse;
import com.livescore.dto.response.TopLeagueResponse;
import com.livescore.service.LeagueInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CommonConstant.API_VERSION)
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueInfoService leagueInfoService;

    @GetMapping(path = "/generate")
    public ResponseEntity<String> generateLeague(){
        leagueInfoService.createLeagueInfo();
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping(path = CommonConstant.TOP_LEAGUE_API_PATH)
    public ResponseEntity<CoreApiResponse> topLeague(){

        List<TopLeagueResponse> topLeagueResponseList = leagueInfoService.getTopLeagues();
        CoreApiResponse coreApiResponse = new CoreApiResponse(CommonConstant.CODE_API_SUCCESS, HttpStatus.OK.value(), topLeagueResponseList);

        return new ResponseEntity<>(coreApiResponse, HttpStatus.OK);
    }

}
