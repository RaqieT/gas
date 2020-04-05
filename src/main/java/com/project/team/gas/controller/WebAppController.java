package com.project.team.gas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class WebAppController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", " Gas");

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
