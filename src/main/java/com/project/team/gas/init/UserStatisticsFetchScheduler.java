package com.project.team.gas.init;

import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.fitness.Fitness;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Component

@RequiredArgsConstructor
public class UserStatisticsFetchScheduler {
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        ClientParametersAuthentication clientParametersAuthentication = new ClientParametersAuthentication("489384042741-clffido2eraa61oo2met5ujd5cnuet7g.apps.googleusercontent.com", "9UoXb5xxIefSBEc6eqRoUY1y");
        Fitness.Builder builder = new Fitness.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientParametersAuthentication);
        builder.setApplicationName("Gainful Achievement System");
        Fitness fitnessApi = builder.build();
        fitnessApi.users().dataSources().list("")
    }
    @Scheduled(fixedDelayString = "${com.project.team.gas.init.UserStatisticsFetchScheduler.fixedDelay}")
    public void pullDataForAllUserStatistics() throws GeneralSecurityException, IOException {
        ClientParametersAuthentication clientParametersAuthentication = new ClientParametersAuthentication("489384042741-clffido2eraa61oo2met5ujd5cnuet7g.apps.googleusercontent.com", "9UoXb5xxIefSBEc6eqRoUY1y");
        Fitness.Builder builder = new Fitness.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientParametersAuthentication);
        builder.setApplicationName("Gainful Achievement System");
        Fitness fitnessApi = builder.build();
        fitnessApi.users().dataSources().list("")

    }
}
