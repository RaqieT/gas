package com.project.team.gas.api.service;

import com.project.team.gas.datastore.Achievement;

import java.util.List;
import java.util.UUID;

public interface AchievementService {

    void save(Achievement achievement);
    List<Achievement> getAllUserAchievements(UUID id);
    List<Achievement> getLastAchievement(UUID id, int offset, int limit);
    void addAchievement();

}
