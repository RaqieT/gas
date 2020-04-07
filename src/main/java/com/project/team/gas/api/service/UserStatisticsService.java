package com.project.team.gas.api.service;

import com.project.team.gas.datastore.UserStatistics;
import org.springframework.stereotype.Service;

@Service
public interface UserStatisticsService {
    void create(UserStatistics userStatistics);
}
