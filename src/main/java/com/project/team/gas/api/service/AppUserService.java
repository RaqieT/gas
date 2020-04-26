package com.project.team.gas.api.service;

import com.project.team.gas.datastore.AppUser;

import java.util.List;
import java.util.UUID;

public interface AppUserService {
    AppUser getMe();
    AppUser get(UUID id);
    List<AppUser> getAll();
}
