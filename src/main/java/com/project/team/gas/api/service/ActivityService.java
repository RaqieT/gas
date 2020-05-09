package com.project.team.gas.api.service;

import com.project.team.gas.datastore.Activity;

import java.util.List;

public interface ActivityService {
    void save(Activity activity, String googleId);
    List<Activity> getAll(int offset, int limit);
    List<Activity> getAllForMe(int offset, int limit);

}
