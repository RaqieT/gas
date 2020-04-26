package com.project.team.gas.controller;

import com.project.team.gas.api.service.AppUserService;
import com.project.team.gas.api.service.UserStatisticsService;
import com.project.team.gas.datastore.AppUser;
import com.project.team.gas.datastore.UserStatistics;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class WebAppController {
    private final AppUserService appUserService;
    private final UserStatisticsService userStatisticsService;
    private final Logger mLog = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        var me = appUserService.getMe();
        var statistics = userStatisticsService.getByUserId(me.getId());

        model.addAttribute("username", me.getName());
        model.addAttribute("user_img", me.getImageUrl());
        model.addAttribute("user_points", statistics.getPoints());
        return "index";
    }

    @RequestMapping("/activities")
    public String activities(Model model){

        return "activities";
    }

    @RequestMapping("/achievement")
    public String achievement(Model model){

        return "achievement";
    }

    @RequestMapping("/topRecords")
    public String topRecords(Model model){

        return "topRecords";
    }

    @RequestMapping("/aboutProgram")
    public String aboutProgram(Model model){

        return "aboutProgram";
    }

}
