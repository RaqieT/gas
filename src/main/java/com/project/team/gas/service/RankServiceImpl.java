package com.project.team.gas.service;

import com.project.team.gas.api.service.AppUserService;
import com.project.team.gas.api.service.RankService;
import com.project.team.gas.api.service.UserStatisticsService;
import com.project.team.gas.datastore.Rank;
import com.project.team.gas.datastore.UserStatistics;
import com.project.team.gas.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class RankServiceImpl implements RankService {

    private final RankRepository rankRepository;
    private final AppUserService appUserService;
    private final UserStatisticsService userStatisticsService;

    @Override
    public void save(Rank rank) {

        var me = appUserService.getMe();
        UserStatistics userStats = userStatisticsService.getByUserId(me.getId());

        whichRank(rank, userStats);

        rankRepository.save(rank);
    }

    @Override
    public void updateRank(Rank rank) {

        var me = appUserService.getMe();
        UserStatistics userStats = userStatisticsService.getByUserId(me.getId());
        rank = rankRepository.findByUserId(me.getId());

        whichRank(rank, userStats);

        rankRepository.save(rank);
    }

    private void whichRank(Rank rank, UserStatistics userStats) {
        if (userStats.getPoints() < 1000) {
            rank.setName("Beginner");
            rank.setRankURL("Beginner.png");
        } else if (userStats.getPoints() >= 1000 && userStats.getPoints() < 5000) {
            rank.setName("Challenger");
            rank.setRankURL("Challenger.png");
        } else if (userStats.getPoints() >= 5000 && userStats.getPoints() < 10000) {
            rank.setName("Amateur");
            rank.setRankURL("Amateur.png");
        } else if (userStats.getPoints() >= 10000 && userStats.getPoints() < 15000) {
            rank.setName("Skillful");
            rank.setRankURL("Skillful.png");
        } else if (userStats.getPoints() >= 15000 && userStats.getPoints() < 20000) {
            rank.setName("Sportsman");
            rank.setRankURL("Sportsman.png");
        } else if (userStats.getPoints() >= 20000 && userStats.getPoints() < 30000) {
            rank.setName("Athlete");
            rank.setRankURL("Athlete.png");
        } else if (userStats.getPoints() >= 30000 && userStats.getPoints() < 50000) {
            rank.setName("Master");
            rank.setRankURL("Master.png");
        } else if (userStats.getPoints() >= 50000) {
            rank.setName("Champion");
            rank.setRankURL("Champion.png");
        }
    }

    @Override
    public List<Rank> getAll() {
        return rankRepository.findAll();
    }

    @Override
    public Rank getByUserId(UUID id) {
        return rankRepository.findByUserId(id);
    }
}
