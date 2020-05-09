package com.project.team.gas.repository;

import com.project.team.gas.datastore.Activity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepository extends BaseRepository<Activity> {
    List<Activity> findAllByOrderByCreatedAt();
    @Query("select a from Activity a join fetch a.userStatistics us join fetch us.user u where u.id = :id order by a.createdAt DESC")
    List<Activity> findAllByUserId(@Param("id") UUID id);
}
