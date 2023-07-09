package com.AquariuxTechTest.Aquariux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.AquariuxTechTest.Aquariux.dto.TransactionDTO;
import com.AquariuxTechTest.Aquariux.dto.UserDTO;
import com.AquariuxTechTest.Aquariux.entity.CryptoCoinPair;
import com.AquariuxTechTest.Aquariux.entity.Transaction;
import com.AquariuxTechTest.Aquariux.entity.User;
import com.AquariuxTechTest.Aquariux.repository.UserRepository;
import com.AquariuxTechTest.Aquariux.service.CoinService;
import com.AquariuxTechTest.Aquariux.service.UserService;

import io.swagger.annotations.Api;

//import io.swagger.annotations.Api;

@RestController
@Api(value = "Main")
@RequestMapping("/api")
public class MainController extends ResponseEntityExceptionHandler {
	
	@Autowired
	CoinService coinService;
	@Autowired
	UserService userService;
	
	@GetMapping("/getPriceInfo")
	public ResponseEntity<List<CryptoCoinPair>> getPriceInfo() {
		//System.out.println("TEST " + this.coinService.getAggregatedPrice());
		//return this.coinService.getAggregatedPrice().toString();
		return new ResponseEntity<>(this.coinService.getAggregatedPrice(),HttpStatus.OK);
		//return "test";
		
	}
	
	@PostMapping("/transaction")
	public ResponseEntity<Boolean> makeTransaction(@Validated @RequestBody TransactionDTO transaction) throws Exception{
		System.out.println("TEST");
		//set userid to 1 for test purposes
		transaction.setUserId(1L);
		this.userService.makeTransaction(transaction);
		return new ResponseEntity<>(true,HttpStatus.OK);
		//return "test";
		
	}
	
	@GetMapping("/getWalletInfo")
	public ResponseEntity<User> getWalletInfo() {
		//set userid to 1 for test purposes
		UserDTO user = new UserDTO();
		user.setId(1L);
		return new ResponseEntity<>(this.userService.getWalletInfo(user),HttpStatus.OK);
		//return "test";
		
	}
	
	@GetMapping("/getTransactionInfo")
	public ResponseEntity<List<Transaction>> getTransactionInfo() {
		//set userid to 1 for test purposes
		UserDTO user = new UserDTO();
		user.setId(1L);
		return new ResponseEntity<>(this.userService.getTransactionInfo(user),HttpStatus.OK);
		//return "test";
		
	}
	
}
