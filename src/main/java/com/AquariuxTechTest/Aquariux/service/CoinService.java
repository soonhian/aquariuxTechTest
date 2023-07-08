package com.AquariuxTechTest.Aquariux.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.AquariuxTechTest.Aquariux.entity.CryptoCoinPair;
import com.AquariuxTechTest.Aquariux.repository.CyptoCoinRepository;

@Service
public class CoinService {

	@Autowired
	CyptoCoinRepository cyptoCoinRepository;
	
	public List<CryptoCoinPair> getAggregatedPrice() {
		//System.out.println("TEST 1 " + this.cyptoCoinRepository.findAll());
		return this.cyptoCoinRepository.findAll();
		
	}

	
}
