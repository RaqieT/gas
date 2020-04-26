package com.project.team.gas.repository;

import com.project.team.gas.datastore.AppUser;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends BaseRepository<AppUser> {
    AppUser findByEmail(String email);
    Boolean existsByEmail(String email);
}
