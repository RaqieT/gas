package com.project.team.gas.integration.google.api.datatype;


import lombok.Data;

import java.io.Serializable;

@Data
public class GoogleOAuth2UserInfo implements Serializable {

    private String id;
    private String name;
    private String email;
    private String imageUrl;
}