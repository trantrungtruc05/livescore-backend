package com.livescore.dto.api.football;

import lombok.Data;

import java.util.List;

@Data
public class AbstractApiFootballDTO {

    private String get;
    private Object parameters;
    private int results;
    private PagingDTO paging;
    private Object errors;
}
