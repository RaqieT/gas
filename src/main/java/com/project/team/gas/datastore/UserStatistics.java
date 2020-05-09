package com.project.team.gas.datastore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class UserStatistics extends BaseEntity {
    private long points;
    @OneToMany(mappedBy = "userStatistics", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Activity> activities;
    @ToString.Exclude
    @OneToOne(mappedBy = "userStatistics", optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private AppUser user;
}
