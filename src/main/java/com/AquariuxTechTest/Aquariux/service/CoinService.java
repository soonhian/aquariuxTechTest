package com.AquariuxTechTest.Aquariux.service;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

public class CoinService {

	public static final String externalBinanceURL = "https://api.binance.com/api/v3/ticker/bookTicker";
	public static final String huobi = "https://api.huobi.pro/market/tickers";

    private  RestTemplate restTemplate;

	
}
