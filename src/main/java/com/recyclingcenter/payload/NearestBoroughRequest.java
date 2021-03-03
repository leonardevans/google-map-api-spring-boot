package com.recyclingcenter.payload;

import com.recyclingcenter.model.RecyclingCenter;

import java.util.List;

public class NearestBoroughRequest {
    private List<RecyclingCenter> centers;
    private double latitude;
    private double longitude;

    public NearestBoroughRequest(List<RecyclingCenter> centers, Long latitude, Long longitude) {
        this.centers = centers;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public List<RecyclingCenter> getCenters() {
        return centers;
    }

    public void setCenters(List<RecyclingCenter> centers) {
        this.centers = centers;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
