package com.project.team.gas.controller;

import com.project.team.gas.api.service.ActivityService;
import com.project.team.gas.api.service.AppUserService;
import com.project.team.gas.api.service.RankService;
import com.project.team.gas.api.service.UserStatisticsService;
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
    private final Logger mLog = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("datetime", new Date());
        var me = appUserService.getMe();
        var statistics = userStatisticsService.getByUserId(me.getId());
        Rank rank;

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
        return "index";
    }


}
