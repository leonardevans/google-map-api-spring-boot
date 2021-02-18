package com.recyclingcenter.repository;

import com.recyclingcenter.model.Location;
import com.recyclingcenter.model.RecyclingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingCenterRepository extends JpaRepository<RecyclingCenter, Long> {
    RecyclingCenter findAllByLocation(Location location);
}
