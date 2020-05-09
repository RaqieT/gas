package com.project.team.gas.api.service;

import com.project.team.gas.datastore.Rank;

import java.util.List;
import java.util.UUID;

public interface RankService {

    void save(Rank rank);
    void updateRank(Rank rank);
    List<Rank> getAll();
    Rank getByUserId(UUID id);

}
