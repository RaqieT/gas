package com.project.team.gas.service;

import com.project.team.gas.api.service.AchievementService;
import com.project.team.gas.api.service.AppUserService;
import com.project.team.gas.api.service.UserStatisticsService;
import com.project.team.gas.datastore.*;
import com.project.team.gas.repository.AchievementRepository;
import com.project.team.gas.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final AppUserService appUserService;
    private final UserStatisticsService userStatisticsService;

    @Override
    public void save(Achievement achievement) {
        achievementRepository.save(achievement);

    }

    @Override
    public void addAchievement(){

        var me = appUserService.getMe();
        UserStatistics userStats = userStatisticsService.getByUserId(me.getId());


        for (Activity activity : userStats.getActivities()) {
            if(achievementRepository.findByName("Hydration") == null && activity.getType().equals(ActivityType.SWIMMING)){
                Achievement achievement = new Achievement();
                achievement.setAppUser(me);
                achievement.setName("Hydration");
                achievement.setAchievementURL("aggregate_hydration.svg");
                achievementRepository.save(achievement);
            }
            else if(activity.getType().equals(ActivityType.RUNNING)){
                if(achievementRepository.findByName("LongDistance") == null && activity.getDistance() > 10){
                    Achievement achievement = new Achievement();
                    achievement.setAppUser(me);
                    achievement.setName("LongDistance");
                    achievement.setAchievementURL("aggregate_distance_delta.svg");
                    achievementRepository.save(achievement);
                }
            }
        }

    }


    @Override
    public List<Achievement> getAllUserAchievements(UUID id) {
        return achievementRepository.findTopByUserId(id);
    }

    @Override
    public List<Achievement> getLastAchievement(UUID id, int offset, int limit) {

        Stream<Achievement> skippedStream = achievementRepository.findTopByUserId(id).stream()
                .skip(offset);
        if (limit != 0) {
            skippedStream = skippedStream.limit(limit);
        }
        return skippedStream.collect(Collectors.toList());
    }

}
