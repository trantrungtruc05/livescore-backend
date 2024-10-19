package com.livescore.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApiFootballServiceConfiguration {

    @Bean
    public ApiFootballRequestInterceptor apiFootballRequestInterceptor(){
        return new ApiFootballRequestInterceptor();
    }
}
