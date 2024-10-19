package com.livescore.dto.api.football.fixtures;

import lombok.Data;

@Data
public class ScoreDTO {

    private ResultInfoPartDTO halftime;
    private ResultInfoPartDTO fulltime;
    private ResultInfoPartDTO extratime;
    private ResultInfoPartDTO penalty;
}
