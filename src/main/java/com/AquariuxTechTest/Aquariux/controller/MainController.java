package com.AquariuxTechTest.Aquariux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AquariuxTechTest.Aquariux.entity.CryptoCoinPair;
import com.AquariuxTechTest.Aquariux.repository.UserRepository;
import com.AquariuxTechTest.Aquariux.service.CoinService;

@RestController
public class MainController {
	
	@Autowired
	CoinService coinService;
	
	@GetMapping("/getInfo")
	public String getUserDetails() {
		System.out.println("TEST " + this.coinService.getAggregatedPrice());
		return this.coinService.getAggregatedPrice().toString();
		//return "test";
		
	}
	

}
