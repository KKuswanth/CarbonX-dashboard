package com.carbonx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ProjectRequest {

	@NotBlank(message = "name is required")
	private String name;

	private String status = "Active";

	@NotNull(message = "credits is required")
	@PositiveOrZero(message = "credits must be zero or positive")
	private Integer credits = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}
}
