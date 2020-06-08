package com.project.team.gas.controller;


import com.project.team.gas.api.service.ActivityService;
import com.project.team.gas.api.service.RankService;
import com.project.team.gas.api.service.UserStatisticsService;
import com.project.team.gas.datastore.UserStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TopRecordsController {
    private final ActivityService activityService;
    private final RankService rankService;
    private final UserStatisticsService userStatisticsService;


    @RequestMapping("/topRecords")
    public String topRecords(Model model){
        List<UserStatistics> lista;

        lista = userStatisticsService.getAllByOrderByPoints();


        model.addAttribute("userStatistics", lista );
        return "topRecords";
    }
}
