package com.project.team.gas.api.service;

import com.project.team.gas.datastore.UserStatistics;

import java.util.List;
import java.util.UUID;

public interface UserStatisticsService {
    void save(UserStatistics userStatistics);
    List<UserStatistics> getAll();
    List<UserStatistics> getAllByOrderByPoints();
    UserStatistics getByUserId(UUID id);

    UserStatistics getByGoogleUserId(String googleId);

    void recalculatePoints(UUID id);
}
