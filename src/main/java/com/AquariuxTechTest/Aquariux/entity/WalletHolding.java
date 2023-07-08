package com.AquariuxTechTest.Aquariux.entity;

import java.util.Set;

import javax.persistence.CascadeType;
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
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "COIN_ID" , referencedColumnName = "id")
		private CryptoCoin coin;
		private Long amount;
		
		
	
}
