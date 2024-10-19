package com.livescore.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "league_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueInfo extends AbstractAuditEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "league_name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "logo")
    private String logo;

    @Column(name = "country")
    private String country;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "country_flag")
    private String countryFlag;
}
