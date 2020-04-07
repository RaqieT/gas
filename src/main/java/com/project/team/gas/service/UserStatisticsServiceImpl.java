package com.project.team.gas.service;

import com.project.team.gas.api.service.UserStatisticsService;
import com.project.team.gas.datastore.UserStatistics;
import com.project.team.gas.repository.UserStatisticsRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserStatisticsServiceImpl implements UserStatisticsService {
    private final UserStatisticsRepository repository;

    @Override
    public void create(UserStatistics userStatistics) {
        repository.save(userStatistics);
    }
}
