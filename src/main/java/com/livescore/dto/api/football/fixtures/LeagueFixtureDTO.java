package com.livescore.dto.api.football.fixtures;

import lombok.Data;

import java.util.Objects;

@Data
public class LeagueFixtureDTO {

    private int id;
    private String name;
    private String country;
    private String logo;
    private String flag;
    private int season;
    private String round;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LeagueFixtureDTO leagueFixtureDTO = (LeagueFixtureDTO) obj;
        return id == leagueFixtureDTO.id && Objects.equals(name, leagueFixtureDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
