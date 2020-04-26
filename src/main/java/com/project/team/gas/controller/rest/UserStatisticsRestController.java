package com.project.team.gas.controller.rest;

import com.project.team.gas.api.dto.StepsUpdateDto;
import com.project.team.gas.api.service.UserStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "rest/open/userstatistics")
@RequiredArgsConstructor
public class UserStatisticsRestController {
    private final UserStatisticsService userStatisticsService;

    @RequestMapping(method = RequestMethod.PUT, path = "steps")
    public void updateSteps(@RequestBody StepsUpdateDto stepsUpdateDto) {
        userStatisticsService.updateSteps(stepsUpdateDto);
    }
    @RequestMapping(method = RequestMethod.GET, path = "steps")
    public String test() {
        return "test";
    }
}
