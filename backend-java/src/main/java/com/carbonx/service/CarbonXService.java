package com.carbonx.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.carbonx.dto.MarketplaceRequest;
import com.carbonx.dto.ProjectRequest;
import com.carbonx.model.PlatformSettings;
import com.carbonx.model.Project;
import com.carbonx.model.Transaction;
import com.carbonx.repository.AppUserRepository;
import com.carbonx.repository.PlatformSettingsRepository;
import com.carbonx.repository.ProjectRepository;
import com.carbonx.repository.SensorReadingRepository;
import com.carbonx.repository.TransactionRepository;

@Service
public class CarbonXService {

	private final ProjectRepository projectRepository;
	private final TransactionRepository transactionRepository;
	private final SensorReadingRepository sensorReadingRepository;
	private final AppUserRepository appUserRepository;
	private final PlatformSettingsRepository platformSettingsRepository;

	public CarbonXService(
			ProjectRepository projectRepository,
			TransactionRepository transactionRepository,
			SensorReadingRepository sensorReadingRepository,
			AppUserRepository appUserRepository,
			PlatformSettingsRepository platformSettingsRepository) {
		this.projectRepository = projectRepository;
		this.transactionRepository = transactionRepository;
		this.sensorReadingRepository = sensorReadingRepository;
		this.appUserRepository = appUserRepository;
		this.platformSettingsRepository = platformSettingsRepository;
	}

	public Map<String, Object> getDashboard() {
		PlatformSettings settings = requireSettings();
		Map<String, Object> response = new HashMap<>();
		response.put("activeProjects", projectRepository.countByStatusIgnoreCase("Active"));
		response.put("carbonCredits", projectRepository.sumCredits());
		response.put("mangroveArea", settings.getMangroveArea());
		response.put("communityImpact", settings.getCommunityImpact());
		return response;
	}

	public List<Project> getProjects() {
		return projectRepository.findAll();
	}

	@Transactional
	public Project createProject(ProjectRequest request) {
		Project project = new Project();
		project.setName(request.getName());
		project.setStatus(request.getStatus() == null || request.getStatus().isBlank()
				? "Active"
				: request.getStatus());
		project.setCredits(request.getCredits() == null ? 0 : request.getCredits());
		return projectRepository.save(project);
	}

	public List<Transaction> getTransactions() {
		return transactionRepository.findAll();
	}

	public Map<String, Object> getMonitoring() {
		PlatformSettings settings = requireSettings();
		List<Map<String, Object>> readings = sensorReadingRepository.findAll().stream()
				.map(reading -> {
					Map<String, Object> item = new HashMap<>();
					item.put("site", reading.getSite());
					item.put("ph", reading.getPh());
					item.put("salinity", reading.getSalinity());
					return item;
				})
				.collect(Collectors.toList());

		Map<String, Object> response = new HashMap<>();
		response.put("treesPlanted", settings.getTreesPlanted());
		response.put("co2Absorbed", settings.getCo2Absorbed());
		response.put("creditsEarned", settings.getCreditsEarned());
		response.put("sensorReadings", readings);
		return response;
	}

	public Map<String, Object> getAdmin() {
		PlatformSettings settings = requireSettings();
		List<Map<String, Object>> users = appUserRepository.findAll().stream()
				.map(user -> {
					Map<String, Object> item = new HashMap<>();
					item.put("id", user.getId());
					item.put("name", user.getName());
					item.put("role", user.getRole());
					return item;
				})
				.collect(Collectors.toList());

		Map<String, Object> settingsMap = new HashMap<>();
		settingsMap.put("platformName", settings.getPlatformName());
		settingsMap.put("version", settings.getVersion());

		Map<String, Object> response = new HashMap<>();
		response.put("users", users);
		response.put("settings", settingsMap);
		return response;
	}

	@Transactional
	public Map<String, Object> buyCredits(MarketplaceRequest request) {
		return createMarketplaceTransaction("BUY", request);
	}

	@Transactional
	public Map<String, Object> sellCredits(MarketplaceRequest request) {
		return createMarketplaceTransaction("SELL", request);
	}

	private Map<String, Object> createMarketplaceTransaction(String type, MarketplaceRequest request) {
		Transaction transaction = new Transaction();
		transaction.setType(type);
		transaction.setProject(request.getProject());
		transaction.setCredits(request.getCredits());
		transaction.setPrice(request.getPrice());
		transaction.setDate(Instant.now().toString());

		Transaction saved = transactionRepository.save(transaction);

		Map<String, Object> response = new HashMap<>();
		response.put("message", type.equals("BUY") ? "Purchase successful!" : "Sale successful!");
		response.put("transaction", saved);
		return response;
	}

	private PlatformSettings requireSettings() {
		return platformSettingsRepository.findById(1L)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Platform settings are not initialized"));
	}
}
