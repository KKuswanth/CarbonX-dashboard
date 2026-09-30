package com.carbonx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carbonx.model.SensorReading;

public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
}
