package com.carbonx.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "platform_settings")
public class PlatformSettings {

	@Id
	private Long id = 1L;

	@Column(nullable = false)
	private String platformName;

	@Column(nullable = false)
	private String version;

	@Column(nullable = false)
	private Integer mangroveArea;

	@Column(nullable = false)
	private Integer communityImpact;

	@Column(nullable = false)
	private Integer treesPlanted;

	@Column(nullable = false)
	private Integer co2Absorbed;

	@Column(nullable = false)
	private Integer creditsEarned;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getMangroveArea() {
		return mangroveArea;
	}

	public void setMangroveArea(Integer mangroveArea) {
		this.mangroveArea = mangroveArea;
	}

	public Integer getCommunityImpact() {
		return communityImpact;
	}

	public void setCommunityImpact(Integer communityImpact) {
		this.communityImpact = communityImpact;
	}

	public Integer getTreesPlanted() {
		return treesPlanted;
	}

	public void setTreesPlanted(Integer treesPlanted) {
		this.treesPlanted = treesPlanted;
	}

	public Integer getCo2Absorbed() {
		return co2Absorbed;
	}

	public void setCo2Absorbed(Integer co2Absorbed) {
		this.co2Absorbed = co2Absorbed;
	}

	public Integer getCreditsEarned() {
		return creditsEarned;
	}

	public void setCreditsEarned(Integer creditsEarned) {
		this.creditsEarned = creditsEarned;
	}
}
