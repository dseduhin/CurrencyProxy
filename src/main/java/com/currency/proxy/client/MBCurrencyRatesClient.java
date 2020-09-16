package com.currency.proxy.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class MBCurrencyRatesClient {
	
	private static final Logger log = LoggerFactory.getLogger(MBCurrencyRatesClient.class);
	
	public MBCurrencyRate[] getMBCurrencyRates() {
		log.info("Requesting currency rates...");
		return new RestTemplate().getForEntity("https://api.monobank.ua/bank/currency", MBCurrencyRate[].class).getBody();
	}
}
