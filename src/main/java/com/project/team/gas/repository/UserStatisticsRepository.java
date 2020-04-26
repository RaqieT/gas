package com.project.team.gas.repository;

import com.project.team.gas.datastore.UserStatistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserStatisticsRepository extends BaseRepository<UserStatistics> {
    @Query("select us from UserStatistics us join fetch us.user u where u.googleUserId = :googleUserId")
    UserStatistics findByUserGoogleId(@Param("googleUserId") String googleUserId);
    @Query("select us from UserStatistics us join fetch us.user u where u.id = :id")
    UserStatistics findByUserId(@Param("id") UUID id);
}
