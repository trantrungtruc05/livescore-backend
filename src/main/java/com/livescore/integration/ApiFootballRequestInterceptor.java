package com.livescore.integration;

import com.livescore.constants.CommonConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class ApiFootballRequestInterceptor implements RequestInterceptor {

    @Value("${api.football.key}")
    private String key;

    @Value("${api.football.host}")
    private String host;
    @Override
    public void apply(RequestTemplate requestTemplate) {

        requestTemplate.header(CommonConstant.API_FOOTBALL_HOST_HEADER, host);
        requestTemplate.header(CommonConstant.API_FOOTBALL_KEY_HEADER, key);

    }
}
