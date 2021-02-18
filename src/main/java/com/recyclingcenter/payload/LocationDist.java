package com.recyclingcenter.payload;

import com.recyclingcenter.model.Location;
import com.recyclingcenter.model.RecyclingCenter;

public class LocationDist {
    private Location location;
    private RecyclingCenter recyclingCenter;
    private double distance;

    public LocationDist(Location location, RecyclingCenter recyclingCenter, double distance) {
        this.location = location;
        this.recyclingCenter = recyclingCenter;
        this.distance = distance;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public RecyclingCenter getRecyclingCenter() {
        return recyclingCenter;
    }

    public void setRecyclingCenter(RecyclingCenter recyclingCenter) {
        this.recyclingCenter = recyclingCenter;
    }
}