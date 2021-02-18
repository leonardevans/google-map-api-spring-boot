package com.recyclingcenter.controller;

import com.recyclingcenter.model.Location;
import com.recyclingcenter.model.RecyclingCenter;
import com.recyclingcenter.payload.LocationDist;
import com.recyclingcenter.repository.LocationRepository;
import com.recyclingcenter.repository.RecyclingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    RecyclingCenterRepository recyclingCenterRepository;

    @PostMapping("/api/search")
    public List<LocationDist> searchLocation(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) {
        List<Location> locations = locationRepository.findAll();
        List<LocationDist> locationDists = new ArrayList<>();

        locations.forEach(location -> {
            double distance = getDistanceFromLatLonInKm(location.getLatitude(), location.getLongitude(), latitude, longitude);
            RecyclingCenter recyclingCenter = recyclingCenterRepository.findAllByLocation(location);
            LocationDist locationDist = new LocationDist(location, recyclingCenter, distance);
            locationDists.add(locationDist);
        });

        locationDists.sort(new SortByDistance());

        List<LocationDist> nearestLocations = new ArrayList<>();
        int total = 0;
        for (LocationDist locationDist : locationDists
        ) {
            nearestLocations.add(locationDist);
            total ++;
            if (total >= 3){break;}
        }
        return nearestLocations;
    }

    public Double getDistanceFromLatLonInKm(double lat1, double lon1, Double lat2, Double lon2) {
        int R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2 - lat1);  // deg2rad below
        double dLon = deg2rad(lon2 - lon1);

        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(deg2rad((double)lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public Double deg2rad(Double deg) {
        return deg * (Math.PI / 180);
    }

    class SortByDistance implements Comparator<LocationDist> {
        // Used for sorting in ascending order of
        // roll number
        public int compare(LocationDist a, LocationDist b) {
            return (int) (a.getDistance() - b.getDistance());
        }
    }
}
