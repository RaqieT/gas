package com.project.team.gas.service;

import com.project.team.gas.api.service.UserStatisticsService;
import com.project.team.gas.datastore.Activity;
import com.project.team.gas.datastore.UserStatistics;
import com.project.team.gas.repository.UserStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public List<UserStatistics> getAll() {
        return repository.findAll();
    }

    @Override
    public UserStatistics getByUserId(UUID id) {
        return repository.findByUserId(id);
    }

    @Override
    public UserStatistics getByGoogleUserId(String googleId) {
        return repository.findByUserGoogleId(googleId);
    }

    @Override
    public void recalculatePoints(UUID id) {
        UserStatistics userStatistics = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Activity> activities = userStatistics.getActivities();
        long points = 0L;
        for (Activity activity : activities) {
            points += activity.calculatePoints();
        }
        userStatistics.setPoints(points);
        save(userStatistics);
    }
}
