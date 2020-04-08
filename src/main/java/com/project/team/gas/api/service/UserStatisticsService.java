package com.project.team.gas.api.service;

import com.project.team.gas.api.dto.StepsUpdateDto;
import com.project.team.gas.datastore.UserStatistics;

import java.util.List;

public interface UserStatisticsService {
    void save(UserStatistics userStatistics);
    void updateSteps(StepsUpdateDto stepsUpdateDto);
    List<UserStatistics> getAll();
}
