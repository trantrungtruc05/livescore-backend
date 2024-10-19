package com.livescore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livescore.constants.CommonConstant;
import com.livescore.dto.api.football.fixtures.FixtureDTO;
import com.livescore.dto.api.football.fixtures.FixtureDetailDTO;
import com.livescore.dto.api.football.fixtures.FixtureResultDTO;
import com.livescore.dto.api.football.fixtures.LeagueFixtureDTO;
import com.livescore.dto.response.CoreApiResponse;
import com.livescore.dto.response.MatchDetailResponse;
import com.livescore.dto.response.MatchInfoResponse;
import com.livescore.entity.TopLeagueConfiguration;
import com.livescore.integration.ApiFootballServiceClient;
import com.livescore.repository.TopLeagueConfigurationRepository;
import com.livescore.utils.DateTimeConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(CommonConstant.API_VERSION)
@RequiredArgsConstructor
public class FixtureController {

    private final ApiFootballServiceClient apiFootballServiceClient;

    private final TopLeagueConfigurationRepository topLeagueConfigurationRepository;

    @GetMapping(path = CommonConstant.API_FOOTBALL_FIXTURES)
    public ResponseEntity<CoreApiResponse> topLeague() throws JsonProcessingException {

        FixtureResultDTO fixtures = apiFootballServiceClient.getFixtures(2024, "2024-10-19", "Asia/Ho_Chi_Minh");
        List<TopLeagueConfiguration> topLeagueConfigurationList = topLeagueConfigurationRepository.findAllByOrderByPriority();
        fixtures.setResponse(fixtures.getResponse().stream().filter(fixtureDTO -> topLeagueConfigurationList.stream().anyMatch(topLeagueConfiguration -> topLeagueConfiguration.getLeagueId() == fixtureDTO.getLeague().getId()))
                        .sorted((s1, s2) -> {
                            int priority1 = topLeagueConfigurationList.stream()
                                    .filter(ageInfo -> ageInfo.getLeagueId() == s1.getLeague().getId())
                                    .findFirst()
                                    .get()
                                    .getPriority();

                            int priority2 = topLeagueConfigurationList.stream()
                                    .filter(ageInfo -> ageInfo.getLeagueId() == s2.getLeague().getId())
                                    .findFirst()
                                    .get()
                                    .getPriority();

                            return Integer.compare(priority1, priority2);
                        })
                .collect(Collectors.toList()));


        List<MatchInfoResponse> matchInfoResponseList = new ArrayList<>();


        for (FixtureDTO fixtureDTO : fixtures.getResponse()) {

            // Kiểm tra xem StudentGroup với key đã tồn tại chưa
            boolean found = false;
            for (MatchInfoResponse matchInfoResponse : matchInfoResponseList) {
                if (matchInfoResponse.getLeague().getId() == fixtureDTO.getLeague().getId()) {
                    MatchDetailResponse matchDetailResponse = new MatchDetailResponse();
                    matchDetailResponse.setFixture(fixtureDTO.getFixture());
                    matchDetailResponse.setTeam(fixtureDTO.getTeams());
                    matchDetailResponse.setGoals(fixtureDTO.getGoals());
                    matchDetailResponse.setScore(fixtureDTO.getScore());

                    matchInfoResponse.addMatches(matchDetailResponse);
                    found = true;
                    break;
                }
            }

            // Nếu không tìm thấy, tạo mới StudentGroup và thêm vào danh sách
            if (!found) {
                MatchInfoResponse matchInfoResponse = new MatchInfoResponse(fixtureDTO.getLeague());

                MatchDetailResponse matchDetailResponse = new MatchDetailResponse();
                matchDetailResponse.setFixture(fixtureDTO.getFixture());
                matchDetailResponse.setTeam(fixtureDTO.getTeams());
                matchDetailResponse.setGoals(fixtureDTO.getGoals());
                matchDetailResponse.setScore(fixtureDTO.getScore());

                matchInfoResponse.addMatches(matchDetailResponse);
                matchInfoResponseList.add(matchInfoResponse);
            }
        }

        CoreApiResponse coreApiResponse = new CoreApiResponse(CommonConstant.CODE_API_SUCCESS, HttpStatus.OK.value(), matchInfoResponseList);

        return new ResponseEntity<>(coreApiResponse, HttpStatus.OK);
    }


    @GetMapping(path = CommonConstant.GET_LIST_DATE_FIXTURES)
    public ResponseEntity<CoreApiResponse> getListOfDay() {

        LocalDate today = LocalDate.now();
        List<String> dateList = new ArrayList<>();

        // Tạo danh sách các ngày từ today - 7 đến today + 7
        for (int i = -7; i <= 7; i++) {
            LocalDate date = today.plusDays(i);
            String formattedDate = DateTimeConvertUtil.formatDateddMM(date);
            dateList.add(formattedDate);
        }

        CoreApiResponse coreApiResponse = new CoreApiResponse(CommonConstant.CODE_API_SUCCESS, HttpStatus.OK.value(), dateList);

        return new ResponseEntity<>(coreApiResponse, HttpStatus.OK);
    }
}
