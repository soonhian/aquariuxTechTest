package com.AquariuxTechTest.Aquariux.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CryptoCoin {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "COIN_NAME")
	private String name;
	
	@Column(name = "COIN_SYMBOL")
	private String symbol;

}
