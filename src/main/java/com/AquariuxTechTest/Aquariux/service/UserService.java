package com.AquariuxTechTest.Aquariux.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AquariuxTechTest.Aquariux.dto.TransactionDTO;
import com.AquariuxTechTest.Aquariux.dto.UserDTO;
import com.AquariuxTechTest.Aquariux.entity.CryptoCoinPair;
import com.AquariuxTechTest.Aquariux.entity.Transaction;
import com.AquariuxTechTest.Aquariux.entity.User;
import com.AquariuxTechTest.Aquariux.entity.Wallet;
import com.AquariuxTechTest.Aquariux.entity.WalletHolding;
import com.AquariuxTechTest.Aquariux.repository.UserRepository;
import com.AquariuxTechTest.Aquariux.repository.CyptoCoinRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CyptoCoinRepository cryptoCoinRepository;
	
	public final static String USDT = "USDT"; 
	public final static String BTC = "BTC"; 
	public final static String ETH = "ETH"; 

	@PostConstruct
	public User createDefaultUser() {
		
		User user = new User();
		Wallet wallet = new Wallet();
		WalletHolding holding = new WalletHolding();
		
		holding.setSymbol("USDT");
		holding.setAmount(Float.parseFloat("50000.00"));

		wallet.setWalletHolding(Arrays.asList(holding));
		wallet.setActiveInd('Y');
		user.setName("admin");
		user.setWallet(wallet);
		
		return userRepository.save(user);
	}
	
	public List<User> getUserInfomation() {
		
		return userRepository.findAll();
	}
	
	public String makeTransaction(TransactionDTO transaction) {
		Optional<User> userOpt = this.userRepository.findById(transaction.getUserId());
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			CryptoCoinPair coinPairOpt = this.cryptoCoinRepository.findBySymbol(transaction.getSymbol());
			List <WalletHolding> walletHolding = user.getWallet().getWalletHolding();

			if (transaction.getTransaction().equals("B")) {
				Float buy = coinPairOpt.getAskPrice();
				System.out.println("buy : " + buy);
				
				String quoteCurrency = transaction.getSymbol().substring(0,transaction.getSymbol().indexOf(USDT));
				
				// transact sell 
				walletHolding.stream().forEach(h -> {
					Boolean transacted = false;
					if(h.getSymbol().equals(USDT) && !transacted) {
						h.setAmount(h.getAmount()- (transaction.getAmount() * buy));
						transacted = true;
					}
				});
				
				// transact buy 
				Boolean transacted = false;
				for(WalletHolding h  : walletHolding) {
					if(h.getSymbol().equals(quoteCurrency) && !transacted) {
						h.setAmount(Float.valueOf(transaction.getAmount().toString()) + h.getAmount());
						transacted = true;
					}
				}
				if(!transacted) {
					WalletHolding holding = new WalletHolding();
					holding.setAmount(Float.valueOf(transaction.getAmount().toString()));
					holding.setSymbol(quoteCurrency);
					walletHolding.add(holding);
				}
				
				System.out.println("walletHolding : " + walletHolding);
				
				//createTransaction
				
				List<Transaction> transList = createTransaction(transaction, user, buy, quoteCurrency,USDT);
				
				
				user.setTransactionList(transList);
				System.out.println("user : " + user);

				
				this.userRepository.save(user);
				

				

			} else if (transaction.getTransaction().equals("S")) {
				Float sell = coinPairOpt.getBidPrice();
				List<String> currency = new ArrayList<>();
				// transact sell 
				walletHolding.stream().forEach(h -> {
					Boolean transacted = false;
					if(transaction.getSymbol().contains(BTC) && h.getSymbol().equals(BTC) && !transacted) {
						h.setAmount(h.getAmount()- transaction.getAmount());
						currency.add(BTC);
						transacted = true;
					}
					if(transaction.getSymbol().contains(ETH) && h.getSymbol().equals(ETH) && !transacted) {
						h.setAmount(h.getAmount()- transaction.getAmount());
						currency.add(ETH);
						transacted = true;
					}
				});
				
				
				// transact buy 
				Boolean transacted = false;
				for(WalletHolding h  : walletHolding) {
					if(h.getSymbol().equals(USDT) && !transacted) {
						h.setAmount((Float.valueOf(transaction.getAmount().toString())*sell) + h.getAmount());
						transacted = true;
					}
				}
				if(!transacted) {
					WalletHolding holding = new WalletHolding();
					holding.setAmount((Float.valueOf(transaction.getAmount().toString())*sell));
					holding.setSymbol(USDT);
					walletHolding.add(holding);
				}
				
				List<Transaction> transList = createTransaction(transaction, user, sell, USDT,currency.get(0));
				user.setTransactionList(transList);
				System.out.println("user : " + user);
				
				this.userRepository.save(user);
				
				
			}
		}
		
		return null;
		
	}

	private List<Transaction> createTransaction(TransactionDTO transaction, User user, Float price,
			String quoteCurrency , String baseCurrency) {
		Transaction trans = new Transaction(transaction.getTransaction(),price,quoteCurrency,baseCurrency,Float.valueOf(transaction.getAmount().toString()));
		
		List<Transaction> transList = user.getTransactionList();
		if(Objects.nonNull(transList)) {
			transList.add(trans);
		}else {
			transList = new ArrayList<>(Arrays.asList(trans));
		}
		return transList;
	}

	public String getWalletInfo(UserDTO user) {
		// TODO Auto-generated method stub
		//this.userRepository.findById(user.getId());
		return this.userRepository.findById(user.getId()).get().toString();
	}

}
