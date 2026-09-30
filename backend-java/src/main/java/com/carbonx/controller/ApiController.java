package com.carbonx.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbonx.dto.MarketplaceRequest;
import com.carbonx.dto.ProjectRequest;
import com.carbonx.model.Project;
import com.carbonx.model.Transaction;
import com.carbonx.service.CarbonXService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

	private final CarbonXService carbonXService;

	public ApiController(CarbonXService carbonXService) {
		this.carbonXService = carbonXService;
	}

	@GetMapping("/dashboard")
	public Map<String, Object> dashboard() {
		return carbonXService.getDashboard();
	}

	@GetMapping("/projects")
	public List<Project> projects() {
		return carbonXService.getProjects();
	}

	@PostMapping("/projects")
	public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectRequest request) {
		return ResponseEntity.ok(carbonXService.createProject(request));
	}

	@GetMapping("/transactions")
	public List<Transaction> transactions() {
		return carbonXService.getTransactions();
	}

	@GetMapping("/monitoring")
	public Map<String, Object> monitoring() {
		return carbonXService.getMonitoring();
	}

	@GetMapping("/admin")
	public Map<String, Object> admin() {
		return carbonXService.getAdmin();
	}

	@PostMapping("/marketplace/buy")
	public ResponseEntity<Map<String, Object>> buy(@Valid @RequestBody MarketplaceRequest request) {
		return ResponseEntity.ok(carbonXService.buyCredits(request));
	}

	@PostMapping("/marketplace/sell")
	public ResponseEntity<Map<String, Object>> sell(@Valid @RequestBody MarketplaceRequest request) {
		return ResponseEntity.ok(carbonXService.sellCredits(request));
	}
}
