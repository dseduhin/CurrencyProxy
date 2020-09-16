package com.currency.proxy.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyRatesConfiguration {
	
	@Bean
	public MBCurrencyRatesClient currencyClient() {
		return new MBCurrencyRatesClient();
	}
	
}
