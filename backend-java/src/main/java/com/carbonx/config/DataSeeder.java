package com.carbonx.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.carbonx.model.AppUser;
import com.carbonx.model.PlatformSettings;
import com.carbonx.model.Project;
import com.carbonx.model.SensorReading;
import com.carbonx.model.Transaction;
import com.carbonx.repository.AppUserRepository;
import com.carbonx.repository.PlatformSettingsRepository;
import com.carbonx.repository.ProjectRepository;
import com.carbonx.repository.SensorReadingRepository;
import com.carbonx.repository.TransactionRepository;

@Component
public class DataSeeder implements CommandLineRunner {

	private final ProjectRepository projectRepository;
	private final TransactionRepository transactionRepository;
	private final SensorReadingRepository sensorReadingRepository;
	private final AppUserRepository appUserRepository;
	private final PlatformSettingsRepository platformSettingsRepository;

	public DataSeeder(
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

	@Override
	public void run(String... args) {
		if (projectRepository.count() > 0) {
			return;
		}

		Project mangrove = new Project();
		mangrove.setName("Mangrove Restoration");
		mangrove.setStatus("Active");
		mangrove.setCredits(850);
		projectRepository.save(mangrove);

		Project plastic = new Project();
		plastic.setName("Plastic Cleanup");
		plastic.setStatus("Active");
		plastic.setCredits(451);
		projectRepository.save(plastic);

		Transaction issuance = new Transaction();
		issuance.setType("Credit Issuance");
		issuance.setCredits(120);
		issuance.setBlock("ab184a0fa");
		issuance.setDate("2025-09-20");
		transactionRepository.save(issuance);

		Transaction planting = new Transaction();
		planting.setType("Tree Planting");
		planting.setCredits(90);
		planting.setBlock("b9183f012");
		planting.setDate("2025-09-15");
		transactionRepository.save(planting);

		SensorReading siteA = new SensorReading();
		siteA.setSite("Site A");
		siteA.setPh(7.1);
		siteA.setSalinity(0.35);
		sensorReadingRepository.save(siteA);

		SensorReading siteB = new SensorReading();
		siteB.setSite("Site B");
		siteB.setPh(6.8);
		siteB.setSalinity(0.40);
		sensorReadingRepository.save(siteB);

		AppUser admin = new AppUser();
		admin.setName("Admin User");
		admin.setRole("Super Admin");
		appUserRepository.save(admin);

		AppUser manager = new AppUser();
		manager.setName("Project Manager");
		manager.setRole("Manager");
		appUserRepository.save(manager);

		PlatformSettings settings = new PlatformSettings();
		settings.setId(1L);
		settings.setPlatformName("CarbonX");
		settings.setVersion("1.0.0");
		settings.setMangroveArea(125);
		settings.setCommunityImpact(3);
		settings.setTreesPlanted(5000);
		settings.setCo2Absorbed(1301);
		settings.setCreditsEarned(1301);
		platformSettingsRepository.save(settings);
	}
}
