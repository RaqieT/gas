package com.project.team.gas.controller;

import com.project.team.gas.api.service.*;
import com.project.team.gas.datastore.Rank;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class WebAppController {
    private final AppUserService appUserService;
    private final UserStatisticsService userStatisticsService;
    private final RankService rankService;
    private final ActivityService activityService;
    private final AchievementService achievementService;
    private final Logger mLog = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("datetime", new Date());
        var me = appUserService.getMe();
        var statistics = userStatisticsService.getByUserId(me.getId());
        Rank rank;

        achievementService.addAchievement();

        if (rankService.getByUserId(me.getId()) != null) {
            rank = rankService.getByUserId(me.getId());
            rankService.updateRank(rank);
        } else {
            rank = new Rank();
            rankService.save(rank);
        }


        model.addAttribute("username", me.getName());
        model.addAttribute("user_img", me.getImageUrl());
        model.addAttribute("user_points", statistics.getPoints());
        model.addAttribute("rank_name", rank.getName());
        model.addAttribute("rank_img", rank.getRankURL());
        model.addAttribute("activities", activityService.getAllForMe(0, 3));
        model.addAttribute("achievement", achievementService.getLastAchievement(me.getId(),0,1));

        return "index";
    }


}
