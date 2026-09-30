package com.carbonx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MarketplaceRequest {

	@NotBlank(message = "project is required")
	private String project;

	@NotNull(message = "credits is required")
	@Positive(message = "credits must be positive")
	private Integer credits;

	@NotNull(message = "price is required")
	@Positive(message = "price must be positive")
	private Double price;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
