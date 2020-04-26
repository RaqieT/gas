package com.project.team.gas.service;

import com.project.team.gas.api.service.AppUserService;
import com.project.team.gas.api.service.UserStatisticsService;
import com.project.team.gas.datastore.AppUser;
import com.project.team.gas.datastore.UserStatistics;
import com.project.team.gas.integration.google.api.datatype.GoogleOAuth2UserInfo;
import com.project.team.gas.integration.google.api.datatype.GasGoogleScopes;
import com.project.team.gas.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl extends OidcUserService implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final UserStatisticsService userStatisticsService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        setAccessibleScopes(GasGoogleScopes.SCOPES_NEEDED);
        OidcUser oidcUser = super.loadUser(userRequest);
        Map<String, Object> attributes = oidcUser.getAttributes();
        GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo();
        userInfo.setEmail((String) attributes.get("email"));
        userInfo.setId((String) attributes.get("sub"));
        userInfo.setImageUrl((String) attributes.get("picture"));
        userInfo.setName((String) attributes.get("name"));
        saveUser(userInfo);
        return oidcUser;
    }

    private void saveUser(GoogleOAuth2UserInfo userInfo) {
        AppUser appUser = appUserRepository.findByEmail(userInfo.getEmail());
        if (appUser == null) {
            appUser = new AppUser();
        }
        appUser.setEmail(userInfo.getEmail());
        appUser.setImageUrl(userInfo.getImageUrl());
        appUser.setName(userInfo.getName());
        appUser.setGoogleUserId(userInfo.getId());

        UserStatistics userStatistics = new UserStatistics();
        userStatisticsService.save(userStatistics);

        appUser.setUserStatistics(userStatistics);

        appUserRepository.save(appUser);
    }


    @Override
    public AppUser getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        DefaultOidcUser defaultOidcUser = (DefaultOidcUser) authentication.getPrincipal();
        String email = defaultOidcUser.getEmail();
        return appUserRepository.findByEmail(email);
    }

    @Override
    public AppUser get(UUID id) {
        return appUserRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }
}
