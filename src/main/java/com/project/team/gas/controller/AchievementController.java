package com.project.team.gas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AchievementController {


    @RequestMapping("/achievement")
    public String achievement(Model model){

        return "achievement";
    }
}
