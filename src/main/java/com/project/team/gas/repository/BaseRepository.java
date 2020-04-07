package com.project.team.gas.repository;

import com.google.api.services.fitness.Fitness;
import com.project.team.gas.datastore.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {
}
