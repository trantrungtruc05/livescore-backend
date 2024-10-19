package com.livescore.repository;

import com.livescore.entity.TopLeagueConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopLeagueConfigurationRepository extends JpaRepository<TopLeagueConfiguration, Long> {

    List<TopLeagueConfiguration> findAllByOrderByPriority();
}
