package com.project.team.gas.datastore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class UserStatistics extends BaseEntity {
    private long steps;

    @OneToOne(mappedBy = "userStatistics", optional = false)
    private AppUser user;

    @JsonProperty("points")
    public long getPoints() {
        // TODO: here we can calculate points, for now it is just steps
        return steps;
    }
}
