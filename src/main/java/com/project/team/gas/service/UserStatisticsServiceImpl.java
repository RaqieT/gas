package com.project.team.gas.service;

import com.project.team.gas.api.dto.StepsUpdateDto;
import com.project.team.gas.api.service.UserStatisticsService;
import com.project.team.gas.datastore.UserStatistics;
import com.project.team.gas.repository.UserStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStatisticsServiceImpl implements UserStatisticsService {
    private final UserStatisticsRepository repository;

    @Override
    public void save(UserStatistics userStatistics) {
        repository.save(userStatistics);
    }

    @Override
    public void updateSteps(StepsUpdateDto stepsUpdateDto) {
        UserStatistics statistics = repository.findByUserGoogleId(stepsUpdateDto.getUserId());
        statistics.setSteps(stepsUpdateDto.getSteps());
    }

    @Override
    public List<UserStatistics> getAll() {
        return repository.findAll();
    }
}
