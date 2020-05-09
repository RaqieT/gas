package com.project.team.gas.repository;

import com.project.team.gas.datastore.Rank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RankRepository extends BaseRepository<Rank>{

    @Query("select r from Rank r join fetch r.user u where u.id = :id")
    Rank findByUserId(@Param("id") UUID id);
}
