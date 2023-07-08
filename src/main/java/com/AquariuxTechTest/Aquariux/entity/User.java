package com.AquariuxTechTest.Aquariux.entity;

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
public class User {
@Id
@GeneratedValue
	
	private Long id;
	
	@Column(name = "USER_NAME")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "WALLET_ID" , referencedColumnName = "id")
	private Wallet wallet;
	
}
