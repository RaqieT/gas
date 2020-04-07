package com.project.team.gas.datastore;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class AppUser extends BaseEntity {
    private String googleUserId;
    private String name;
    private String email;
    private String imageUrl;
}
