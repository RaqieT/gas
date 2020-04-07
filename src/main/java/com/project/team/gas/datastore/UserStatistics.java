package com.project.team.gas.datastore;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class UserStatistics extends BaseEntity {
    private long points;
    private String clientId; // TODO: to be removed
}
