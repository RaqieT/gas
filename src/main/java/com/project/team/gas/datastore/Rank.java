package com.project.team.gas.datastore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Rank extends BaseEntity{

    @OneToOne(mappedBy = "rank", optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private AppUser user;

    String name;
    String rankURL;

}
