package com.AquariuxTechTest.Aquariux.dto;

import lombok.Data;

@Data
public class HuobiDTO {

	
	private String symbol;
	private String open;
	private String high;
	private String low;
	private String close;
	private String amount;
	private String vol;
	private String count;
	private String bid;
	private String bidSize;
	private String ask;
	private String askSize;

	
}

//{"symbol":"sylousdt","open":0.001509,"high":0.001521,"low":0.001493,"close":0.001493,"amount":1.130405959627E8,"vol":170544.0769461576,"count":17055,"bid":0.001518,"bidSize":18267.0644,"ask":0.001522,"askSize":16644.281997830374}