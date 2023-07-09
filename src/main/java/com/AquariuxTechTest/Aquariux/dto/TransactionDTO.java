package com.AquariuxTechTest.Aquariux.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionDTO {
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long userId;
	private String transaction;
	private Long amount;
	private String symbol;
	
}
