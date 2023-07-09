package com.AquariuxTechTest.Aquariux.dto;
import lombok.Data;

@Data
public class TransactionDTO {
	
	private Long userId;
	private String transaction;
	private Long amount;
	private String symbol;
	
}
