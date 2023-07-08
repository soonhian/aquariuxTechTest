package com.AquariuxTechTest.Aquariux.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Wallet {
@Id
@GeneratedValue
	
	private Long id;

	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "WALLET_ID" , referencedColumnName = "id")
	private List<WalletHolding> walletHolding;
	
	@Column(name = "ACTIVE_IND")
	private Character activeInd;
	
}
