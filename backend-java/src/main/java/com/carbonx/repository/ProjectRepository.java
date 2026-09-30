package com.carbonx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carbonx.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	long countByStatusIgnoreCase(String status);

	@Query("select coalesce(sum(p.credits), 0) from Project p")
	Integer sumCredits();
}
