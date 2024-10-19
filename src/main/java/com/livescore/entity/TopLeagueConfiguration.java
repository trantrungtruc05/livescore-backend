package com.livescore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "top_league_configuration")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopLeagueConfiguration {

    @Id
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "league_id")
    private Integer leagueId;

    @Column(name = "priority")
    private Integer priority;
}
