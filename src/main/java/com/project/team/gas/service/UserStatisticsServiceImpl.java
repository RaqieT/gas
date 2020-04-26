package com.project.team.gas.service;

import com.project.team.gas.api.dto.StepsUpdateDto;
import com.project.team.gas.api.service.UserStatisticsService;
import com.project.team.gas.datastore.UserStatistics;
import com.project.team.gas.repository.UserStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        UserStatistics statistics = repository.findByUserGoogleId(stepsUpdateDto.getGoogleUserId());
        statistics.setSteps(stepsUpdateDto.getSteps());
        repository.save(statistics);
    }

    @Override
    public List<UserStatistics> getAll() {
        return repository.findAll();
    }

    @Override
    public UserStatistics getByUserId(UUID id) {
        return repository.findByUserId(id);
    }
}
