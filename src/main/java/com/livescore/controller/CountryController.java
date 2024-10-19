package com.livescore.controller;

import com.livescore.constants.CommonConstant;
import com.livescore.dto.response.CoreApiResponse;
import com.livescore.dto.response.CountryDTO;
import com.livescore.service.CountryService;
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
public class CountryController {

    private final CountryService countryService;

    @GetMapping(path = CommonConstant.GET_ALL_COUNTRY_API_PATH)
    public ResponseEntity<CoreApiResponse> topLeague(){

        List<CountryDTO> getAllCountry = countryService.getAllCountry();
        CoreApiResponse coreApiResponse = new CoreApiResponse(CommonConstant.CODE_API_SUCCESS, HttpStatus.OK.value(), getAllCountry);

        return new ResponseEntity<>(coreApiResponse, HttpStatus.OK);
    }
}
