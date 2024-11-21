package com.livescore.repository;

import com.livescore.entity.LeagueInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueInfoRepository extends JpaRepository<LeagueInfo, Integer> {

    List<LeagueInfo> findAllByOrderByPriority();

    List<LeagueInfo> findAllByPriorityNotNullOrderByPriority();

    @Modifying(clearAutomatically = true)
    @Query("UPDATE LeagueInfo li SET li.priority = :priority WHERE li.id = :id")
    void updatePriorityLeagueInfo(@Param("priority") Integer priority, @Param("id") Integer id);

}
