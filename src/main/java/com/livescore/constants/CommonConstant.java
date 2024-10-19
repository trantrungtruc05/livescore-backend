package com.livescore.constants;

public class CommonConstant {

    public static final String API_FOOTBALL_HOST_HEADER = "x-rapidapi-host";
    public static final String API_FOOTBALL_KEY_HEADER = "x-rapidapi-key";
    public static final String API_FOOTBALL_LEAGUES = "/leagues";

    public static final String API_FOOTBALL_COUNTRIES = "/countries";

    public static final String API_FOOTBALL_FIXTURES = "/fixtures";

    public static final String API_VERSION = "/v1";
    public static final String TOP_LEAGUE_API_PATH = "/top-league";

    public static final String GET_ALL_COUNTRY_API_PATH = "/countries";

    public static final String GET_LIST_DATE_FIXTURES = "/list-of-day";

    public static final int CODE_API_SUCCESS = 0;

    private CommonConstant(){ throw new IllegalStateException();}
}
