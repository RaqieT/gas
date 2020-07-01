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
    @JsonIgnore
    private double velocity;

    @JsonProperty("velocity")
    public String serializeVelocity() {
        return velocity + " m/s";
    }

    @JsonProperty("distance")
    public String serializeDistance() {
        return (velocity * time / 1000) + " m";
    }

    @JsonProperty("time")
    public String serializeTime() {
       return String.format("%d:%02d:%02d", time / (60*60*1000L), (time % (60*60*1000)) / (60*1000), (time % (60*1000)) / 1000);
    }

    @JsonProperty("points")
    public long calculatePoints() {
        double multiplier = 0;

        switch (type) {
            case CYCLING:
                multiplier = 1d;
                break;
            case WALKING:
            case RUNNING:
                multiplier = 2.5d;
                break;
            case SWIMMING:
                multiplier = 25d;
                break;
        }
        return (long) ((( velocity * (time/1000)) / 20) * multiplier);
    }
}
