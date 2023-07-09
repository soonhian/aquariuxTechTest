package com.AquariuxTechTest.Aquariux.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
@Data
@Entity
public class Transaction {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "FEE_ACTION")
	private String transaction;
	
	@Column(name = "PRICE")
	private Float price;
		
	@Column(name = "QUOTE_CURRENCY")
	private String quoteCurrency;
	
	@Column(name = "BASE_CURRENCY")
	private String baseCurrency;
	
	@Column(name = "AMOUNT")
	private Float amount;
	
	@Column(name = "DATE")
	private LocalDateTime dateTime;
	
	public  Transaction(String feeAction,Float price , String quoteCurrency,String baseCurrency,Float amount) {
		this.transaction = feeAction;
		this.price = price;
		this.quoteCurrency = quoteCurrency;
		this.baseCurrency = baseCurrency;
		this.amount = amount;
		this.dateTime = LocalDateTime.now();
	}
	
	public Transaction() {
		
	}
	
}
