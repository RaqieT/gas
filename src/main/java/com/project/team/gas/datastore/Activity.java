package com.project.team.gas.datastore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.time.temporal.TemporalUnit;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Activity extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    private UserStatistics userStatistics;
    private ActivityType type;
    @JsonIgnore
    private double distance; // in meters
    @JsonIgnore
    private long time; // in milliseconds

    @JsonProperty("distance")
    public String serializeDistance() {
        return String.format("%.2f", distance/1000) + " km";
    }

    @JsonProperty("time")
    public String serializeTime() {
       return String.format("%d:%02d:%02d", time / (60*60*1000L), (time % (60*60*1000)) / (60*1000), (time % (60*1000)) / 1000);
    }

    @JsonProperty("points")
    public long calculatePoints() {
        double multiplier = 0;

        switch (type) {
            case WALKING:
                multiplier = 1d;
                break;
            case CYCLING:
                multiplier = 1.5d;
                break;
            case RUNNING:
                multiplier = 2d;
                break;
            case SWIMMING:
                multiplier = 3d;
                break;
        }
        return (long) ( multiplier * distance * (1d / (time / 100000d)) );
    }
}
