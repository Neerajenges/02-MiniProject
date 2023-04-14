package com.eg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.entity.EligibilityDetails;

public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDetails, Integer> {

}
