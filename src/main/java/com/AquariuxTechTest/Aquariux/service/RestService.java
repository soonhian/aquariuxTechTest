package com.AquariuxTechTest.Aquariux.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.AquariuxTechTest.Aquariux.dto.BinanceDTO;
import com.AquariuxTechTest.Aquariux.dto.HuobiDTO;
import com.AquariuxTechTest.Aquariux.dto.JsonResponse;
import com.AquariuxTechTest.Aquariux.entity.CryptoCoinPair;
import com.AquariuxTechTest.Aquariux.entity.User;
import com.AquariuxTechTest.Aquariux.repository.CyptoCoinRepository;
import com.AquariuxTechTest.Aquariux.repository.UserRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class RestService {
	
	public static final String externalBinanceURL = "https://api.binance.com/api/v3/ticker/bookTicker";
	public static final String huobi = "https://api.huobi.pro/market/tickers";

    private final RestTemplate restTemplate;

    private ConcurrentHashMap<String, CryptoCoinPair> coinHashMap = new ConcurrentHashMap<String, CryptoCoinPair>();
    
	@Autowired
	CyptoCoinRepository cyptoCoinRepository;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
    @Scheduled(fixedRate = 10000)
	public void reportCurrentTime() {
    	//coinHashMap = new ConcurrentHashMap<String, CryptoCoinPair>();
		getExternalBinanceURL();
		getExternalHuobiURL();
		
		 // Getting Collection of values from HashMap
        Collection<CryptoCoinPair> values = coinHashMap.values();
  
        // Creating an ArrayList of values
        cyptoCoinRepository.saveAll(new ArrayList<>(values));

	}
    
    public String getExternalBinanceURL() {
        
    	
    	ObjectMapper objectMapper = new ObjectMapper();

        //read JSON file and convert to a customer object
    	List<BinanceDTO> dto = new ArrayList<>();
		try {
			dto = objectMapper.readValue(this.restTemplate.getForObject(externalBinanceURL, String.class),
					new TypeReference<List<BinanceDTO>>(){});
	        dto.stream().forEach(d ->{
	        	//if(d.getSymbol().toUpperCase().equals("ETHUSDT") || d.getSymbol().toUpperCase().equals("BTCUSDT")) {
			        	if(coinHashMap.containsKey(d.getSymbol().toUpperCase())) {
			        		CryptoCoinPair coin = coinHashMap.get(d.getSymbol().toUpperCase());
			        		if(coin.getAskPrice() < Float.parseFloat(d.getAskPrice())) {
			        			coin.setAskPrice(Float.parseFloat(d.getAskPrice()));
			        			coin.setAskSource("binance");
			        		}
			        		if(coin.getBidPrice() < Float.parseFloat(d.getBidPrice())) {
			        			coin.setBidPrice(Float.parseFloat(d.getAskPrice()));
			        			coin.setBidSource("binance");
			        		}
			        		coinHashMap.put(coin.getSymbol().toUpperCase(),coin);
			        	}else {
			        		coinHashMap.put(d.getSymbol().toUpperCase(),new CryptoCoinPair(d.getSymbol().toUpperCase(),Float.parseFloat(d.getBidPrice()),Float.parseFloat(d.getAskPrice())
			        				,"Binance","Binance",null,null) );
			        	}
	        	//}
	        } );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //print customer details
    	
    	return this.restTemplate.getForObject(externalBinanceURL, String.class);
    }
    
    public String getExternalHuobiURL() {
        
    	
    	ObjectMapper objectMapper = new ObjectMapper();

        //read JSON file and convert to a customer object
    	List<HuobiDTO> dto = new ArrayList<>();
		try {
			dto = objectMapper.convertValue(this.restTemplate.getForObject(huobi, JsonResponse.class).getData(),
					new TypeReference<List<HuobiDTO>>(){});
			  dto.stream().forEach(d ->{
		        	//if(d.getSymbol().toUpperCase().equals("ETHUSDT") || d.getSymbol().toUpperCase().equals("BTCUSDT")) {
		        	if(coinHashMap.containsKey(d.getSymbol().toUpperCase())) {
		        		CryptoCoinPair coin = coinHashMap.get(d.getSymbol().toUpperCase());
		        		if(coin.getAskPrice() < Float.parseFloat(d.getAsk())) {
		        			coin.setAskPrice(Float.parseFloat(d.getAsk()));
		        			coin.setAskSource("huobi");
		        		}
		        		if(coin.getBidPrice() < Float.parseFloat(d.getBid())) {
		        			coin.setBidPrice(Float.parseFloat(d.getBid()));
		        			coin.setBidSource("huobi");
		        		}
		        		coinHashMap.put(coin.getSymbol().toUpperCase(),coin);
		        	}else {
		        		coinHashMap.put(d.getSymbol().toUpperCase(),new CryptoCoinPair(d.getSymbol().toUpperCase(),Float.parseFloat(d.getBid())
		        				,Float.parseFloat(d.getAsk()),"huobi","huobi",null,null) );
		        	}
		        	//}
		        } );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
    	return this.restTemplate.getForObject(huobi, String.class);
    }
    
	@PostConstruct
	public void getExternalInformation() {
		//getExternalBinanceURL();
		//getExternalHuobiURL();
	}
    
    
}
