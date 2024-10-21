package com.livescore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.livescore.constants.CommonConstant;
import com.livescore.dto.api.football.fixtures.FixtureDTO;
import com.livescore.dto.api.football.fixtures.FixtureResultDTO;
import com.livescore.dto.response.CoreApiResponse;
import com.livescore.dto.response.DateListDTO;
import com.livescore.dto.response.MatchDetailResponse;
import com.livescore.dto.response.MatchInfoResponse;
import com.livescore.entity.LeagueInfo;
import com.livescore.integration.ApiFootballServiceClient;
import com.livescore.repository.LeagueInfoRepository;
import com.livescore.utils.DateTimeConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(CommonConstant.API_VERSION)
@RequiredArgsConstructor
public class FixtureController {

    private final ApiFootballServiceClient apiFootballServiceClient;

    private final LeagueInfoRepository leagueInfoRepository;

    @GetMapping(path = CommonConstant.API_FOOTBALL_FIXTURES)
    public ResponseEntity<CoreApiResponse> listFixtureByCondition(@RequestParam(value = "season", required = false) Integer season, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "status", required = false) String status, @RequestParam(value = "live", required = false) String live, @RequestParam("timezone") String timezone) {

        FixtureResultDTO fixtures = apiFootballServiceClient.getFixtures(season, date, status, live, timezone);
        List<LeagueInfo> leagueInfoList = leagueInfoRepository.findAllByOrderByPriority();
        fixtures.setResponse(fixtures.getResponse().stream()
                        .sorted((s1, s2) -> {
                            Integer priority1 = leagueInfoList.stream()
                                    .filter(leagueInfo -> leagueInfo.getId() == s1.getLeague().getId())
                                    .findFirst()
                                    .get()
                                    .getPriority();

                            Integer priority2 = leagueInfoList.stream()
                                    .filter(leagueInfo -> leagueInfo.getId() == s2.getLeague().getId())
                                    .findFirst()
                                    .get()
                                    .getPriority();

                            if (priority1 == null && priority2 == null) {
                                return 0;
                            } else if (priority1 == null) {
                                return 1;
                            } else if (priority2 == null) {
                                return -1;
                            } else {
                                return Integer.compare(priority1, priority2);
                            }
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
        List<DateListDTO> dateList = new ArrayList<>();

        // Tạo danh sách các ngày từ today - 7 đến today + 7
        for (int i = -7; i <= 7; i++) {

            LocalDate date = today.plusDays(i);
            String value = DateTimeConvertUtil.formatDateyyyyMMdd(date);
            String text = DateTimeConvertUtil.formatDateddMM(date);

            dateList.add(new DateListDTO(value, text));
        }

        CoreApiResponse coreApiResponse = new CoreApiResponse(CommonConstant.CODE_API_SUCCESS, HttpStatus.OK.value(), dateList);

        return new ResponseEntity<>(coreApiResponse, HttpStatus.OK);
    }
}
