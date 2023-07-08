package com.AquariuxTechTest.Aquariux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AquariuxTechTest.Aquariux.entity.CryptoCoinPair;
import com.AquariuxTechTest.Aquariux.entity.User;

public interface CyptoCoinRepository extends JpaRepository<CryptoCoinPair, Long> {
	
	

}
