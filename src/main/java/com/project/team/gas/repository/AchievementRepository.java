package com.project.team.gas.repository;

import com.project.team.gas.datastore.Achievement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AchievementRepository extends BaseRepository<Achievement>{

    @Query("select a from Achievement a join fetch a.appUser us where us.id = :id order by a.createdAt DESC")
    List<Achievement> findTopByUserId(@Param("id") UUID id);

    Achievement findByName(String name);

}
