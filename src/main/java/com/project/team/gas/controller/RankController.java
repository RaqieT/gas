package com.project.team.gas.controller;

import com.project.team.gas.api.service.AppUserService;
import com.project.team.gas.api.service.RankService;
import com.project.team.gas.datastore.Rank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;
    private final AppUserService appUserService;
    @RequestMapping("/rank")
    public String rank(Model model){

        Rank rank;
        var me = appUserService.getMe();

        if (rankService.getByUserId(me.getId()) != null) {
            rank = rankService.getByUserId(me.getId());
            rankService.updateRank(rank);
        } else {
            rank = new Rank();
            rankService.save(rank);
        }

        model.addAttribute("rank_name", rank.getName());
        return "rank";
    }
}
