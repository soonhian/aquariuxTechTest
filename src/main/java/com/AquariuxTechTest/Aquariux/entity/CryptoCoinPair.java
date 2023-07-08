package com.AquariuxTechTest.Aquariux.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CryptoCoinPair {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "COINPAIR_SYMBOL")
	private String symbol;
	
	@Column(name = "QUOTE_CURRENCY")
	private String quoteCurrency;
	
	@Column(name = "BASE_CURRENCY")
	private String baseCurrency;
	
	@Column(name = "ASK_PRICE")
	private Float askPrice;
	
	@Column(name = "BID_PRICE")
	private Float bidPrice;
	
	@Column(name = "BID_SOURCE")
	private String bidSource;
	
	@Column(name = "ASK_SOURCE")
	private String askSource;
	
	public CryptoCoinPair( String symbol , Float bidPrice,Float askPrice,String bidSource, String askSource,String baseCurrency,String quoteCurrency) {
		this.symbol = symbol;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
		this.bidSource = bidSource;
		this.askSource = askSource;
		this.baseCurrency = baseCurrency;
		this.quoteCurrency = quoteCurrency;
	}

	public CryptoCoinPair() {
		// TODO Auto-generated constructor stub
	}

}
