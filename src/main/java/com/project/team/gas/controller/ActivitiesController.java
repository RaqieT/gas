package com.project.team.gas.controller;

import com.project.team.gas.api.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ActivitiesController {

    private final ActivityService activityService;

    @RequestMapping("/activities")
    public String activities(Model model){


        model.addAttribute("activities", activityService.getAllForMe(0, 0));
        return "activities";
    }
}
