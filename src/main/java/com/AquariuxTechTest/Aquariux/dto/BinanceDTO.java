package com.AquariuxTechTest.Aquariux.dto;

import lombok.Data;

@Data
public class BinanceDTO {
	
	private String symbol;
	private String bidPrice;
	private String bidQty;
	private String askPrice;
	private String askQty;

}
