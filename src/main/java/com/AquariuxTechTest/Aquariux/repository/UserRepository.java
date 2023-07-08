package com.AquariuxTechTest.Aquariux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AquariuxTechTest.Aquariux.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	

}
