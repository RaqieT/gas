package com.project.team.gas.datastore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Rank extends BaseEntity{

    @OneToOne(mappedBy = "rank", optional = false)
    private AppUser user;

    String name;
    String rankURL;

}
