package com.project.team.gas.controller;

import com.project.team.gas.api.service.AchievementService;
import com.project.team.gas.api.service.AppUserService;
import com.project.team.gas.datastore.Achievement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;
    private final AppUserService appUserService;

    @RequestMapping("/achievement")
    public String achievement(Model model) {

        List<String> namesOfAchievements = new ArrayList<>();

        var me = appUserService.getMe();
        for (Achievement achievement : achievementService.getAllUserAchievements(me.getId())) {
            namesOfAchievements.add(achievement.getName());
        }

        model.addAttribute("achievements", achievementService.getAllUserAchievements(me.getId()));
        model.addAttribute("namesOfAchievements", namesOfAchievements);
        return "achievement";
    }
}
