package com.project.team.gas.controller.rest;

import com.project.team.gas.api.service.ActivityService;
import com.project.team.gas.datastore.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "rest/open/activity")
@RequiredArgsConstructor
public class ActivityRestController {
    private final ActivityService activityService;

    @RequestMapping(method = RequestMethod.POST, value = "/google-id/{id}" )
    public void updateSteps(@RequestBody Activity activity, @PathVariable("id") String id) {
        activityService.save(activity, id);
    }
}
