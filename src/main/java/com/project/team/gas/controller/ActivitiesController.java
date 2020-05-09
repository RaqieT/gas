package com.project.team.gas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ActivitiesController {


    @RequestMapping("/activities")
    public String activities(Model model){

        return "activities";
    }
}
