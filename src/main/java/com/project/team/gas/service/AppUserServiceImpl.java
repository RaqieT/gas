package com.project.team.gas.service;

import com.project.team.gas.api.service.AppUserService;
import com.project.team.gas.datastore.AppUser;
import com.project.team.gas.integration.google.api.datatype.GoogleOAuth2UserInfo;
import com.project.team.gas.integration.google.api.datatype.GoogleOAuthScopes;
import com.project.team.gas.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl extends OidcUserService implements AppUserService {

    private final AppUserRepository appUserRepository;
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        setAccessibleScopes(Set.of(OidcScopes.PROFILE, OidcScopes.EMAIL, GoogleOAuthScopes.FITNESS));
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
        appUserRepository.save(appUser);
    }


}
