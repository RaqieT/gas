package com.project.team.gas.init;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
public class UserStatisticsFetchScheduler {
    @Scheduled(fixedDelayString = "${com.project.team.gas.init.UserStatisticsFetchScheduler.fixedDelay}")
    public void pullDataForAllUserStatistics() throws GeneralSecurityException, IOException {

    }
}
