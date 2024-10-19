package com.livescore.dto.api.football.fixtures;

import lombok.Data;

@Data
public class TeamDetailDTO {

    private int id;
    private String name;
    private String logo;
    private Object winner;
}
