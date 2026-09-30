-- MySQL schema for CarbonX (optional manual setup)
-- Run once: CREATE DATABASE carbonx; USE carbonx;

CREATE TABLE IF NOT EXISTS projects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    credits INT NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100) NOT NULL,
    project VARCHAR(255),
    credits INT NOT NULL,
    price DOUBLE,
    block VARCHAR(100),
    date VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS sensor_readings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) NOT NULL,
    ph DOUBLE NOT NULL,
    salinity DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS app_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS platform_settings (
    id BIGINT PRIMARY KEY,
    platform_name VARCHAR(255) NOT NULL,
    version VARCHAR(50) NOT NULL,
    mangrove_area INT NOT NULL,
    community_impact INT NOT NULL,
    trees_planted INT NOT NULL,
    co2_absorbed INT NOT NULL,
    credits_earned INT NOT NULL
);
