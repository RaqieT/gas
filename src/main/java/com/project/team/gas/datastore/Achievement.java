package com.project.team.gas.datastore;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Achievement extends BaseEntity {



}
