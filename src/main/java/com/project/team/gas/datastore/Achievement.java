package com.project.team.gas.datastore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Achievement extends BaseEntity {

    @ManyToOne
    @JsonIgnore
    private AppUser appUser;

    String name;
    String achievementURL;
    String description;

}
