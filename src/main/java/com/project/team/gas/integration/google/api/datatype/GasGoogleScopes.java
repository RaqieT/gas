package com.project.team.gas.integration.google.api.datatype;

import org.springframework.security.oauth2.core.oidc.OidcScopes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GasGoogleScopes {
    public static final Set<String> SCOPES_NEEDED = new HashSet<>(Arrays.asList(OidcScopes.OPENID, OidcScopes.PROFILE, OidcScopes.EMAIL));
}
