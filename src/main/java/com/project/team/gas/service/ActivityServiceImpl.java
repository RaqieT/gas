package com.project.team.gas.service;

import com.project.team.gas.api.service.ActivityService;
import com.project.team.gas.api.service.AppUserService;
import com.project.team.gas.api.service.UserStatisticsService;
import com.project.team.gas.datastore.Activity;
import com.project.team.gas.datastore.AppUser;
import com.project.team.gas.datastore.UserStatistics;
import com.project.team.gas.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final UserStatisticsService userStatisticsService;
    private final AppUserService appUserService;

    @Override
    public void save(Activity activity, String googleId) {
        UserStatistics byGoogleUserId = userStatisticsService.getByGoogleUserId(googleId);
        if (byGoogleUserId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, UserStatistics.class.getName());
        }
        activity.setUserStatistics(byGoogleUserId);
        activityRepository.save(activity);
        userStatisticsService.recalculatePoints(byGoogleUserId.getId());
    }

    @Override
    public List<Activity> getAll(int offset, int limit) {
        Stream<Activity> skippedStream = activityRepository.findAllByOrderByCreatedAt().stream()
                .skip(offset);
        if (limit != 0) {
            skippedStream = skippedStream.limit(limit);
        }
        return skippedStream.collect(Collectors.toList());
    }

    @Override
    public List<Activity> getAllForMe(int offset, int limit) {
        AppUser me = appUserService.getMe();
        Stream<Activity> skippedStream = activityRepository.findAllByUserId(me.getId()).stream()
                .skip(offset);
        if (limit != 0) {
            skippedStream = skippedStream.limit(limit);
        }
        return skippedStream.collect(Collectors.toList());
    }
}
