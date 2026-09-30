package com.carbonx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carbonx.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
