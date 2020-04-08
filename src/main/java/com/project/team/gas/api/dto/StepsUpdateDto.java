package com.project.team.gas.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StepsUpdateDto implements Serializable {
    private String userId;
    private int steps;
}
