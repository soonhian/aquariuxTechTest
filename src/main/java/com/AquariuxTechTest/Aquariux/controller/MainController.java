package com.AquariuxTechTest.Aquariux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AquariuxTechTest.Aquariux.dto.TransactionDTO;
import com.AquariuxTechTest.Aquariux.dto.UserDTO;
import com.AquariuxTechTest.Aquariux.entity.CryptoCoinPair;
import com.AquariuxTechTest.Aquariux.repository.UserRepository;
import com.AquariuxTechTest.Aquariux.service.CoinService;
import com.AquariuxTechTest.Aquariux.service.UserService;

import io.swagger.annotations.Api;

//import io.swagger.annotations.Api;

@RestController
@Api(value = "Main")
@RequestMapping("/api")
public class MainController {
	
	@Autowired
	CoinService coinService;
	@Autowired
	UserService userService;
	
	@GetMapping("/getPriceInfo")
	public String getPriceInfo() {
		//System.out.println("TEST " + this.coinService.getAggregatedPrice());
		return this.coinService.getAggregatedPrice().toString();
		//return "test";
		
	}
	
	@PostMapping("/transaction")
	public String makeTransaction(@Validated @RequestBody TransactionDTO transaction) {
		System.out.println("TEST");
		//set userid to 1 for test purposes
		transaction.setUserId(1L);
		return this.userService.makeTransaction(transaction);
		//return "test";
		
	}
	
	@GetMapping("/getWalletInfo")
	public String getWalletInfo() {
		//set userid to 1 for test purposes
		UserDTO user = new UserDTO();
		user.setId(1L);
		return this.userService.getWalletInfo(user).toString();
		//return "test";
		
	}
	
	

}
