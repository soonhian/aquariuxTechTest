package com.AquariuxTechTest.Aquariux.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class WalletHolding {
	@Id
	@GeneratedValue
		
		private Long id;
	
		@Column(name = "AMOUNT")
		private Float amount;
		
		@Column(name = "SYMBOL")
		private String symbol;
		
		
	
}
