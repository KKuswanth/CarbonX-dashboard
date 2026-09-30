package com.carbonx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carbonx.model.PlatformSettings;

public interface PlatformSettingsRepository extends JpaRepository<PlatformSettings, Long> {
}
