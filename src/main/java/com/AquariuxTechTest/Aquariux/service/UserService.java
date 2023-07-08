package com.AquariuxTechTest.Aquariux.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AquariuxTechTest.Aquariux.entity.CryptoCoinPair;
import com.AquariuxTechTest.Aquariux.entity.User;
import com.AquariuxTechTest.Aquariux.entity.Wallet;
import com.AquariuxTechTest.Aquariux.entity.WalletHolding;
import com.AquariuxTechTest.Aquariux.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	public User createDefaultUser() {
		
		User user = new User();
		Wallet wallet = new Wallet();
		WalletHolding holding = new WalletHolding();
		
		holding.setSymbol("USDT");
		holding.setAmount(Float.parseFloat("50000.00"));

		wallet.setWalletHolding(new HashSet<WalletHolding>(Arrays.asList(holding)));
		wallet.setActiveInd('Y');
		user.setName("admin");
		user.setWallet(wallet);
		
		return userRepository.save(user);
	}
	
	public List<User> getUserInfomation() {
		
		return userRepository.findAll();
	}

}
