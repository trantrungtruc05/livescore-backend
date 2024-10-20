package com.livescore.repository;

import com.livescore.entity.LeagueInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueInfoRepository extends JpaRepository<LeagueInfo, Integer> {

    List<LeagueInfo> findAllByOrderByPriority();

    List<LeagueInfo> findAllByPriorityNotNullOrderByPriority();

}
