package com.currency.proxy.repository;

import com.currency.proxy.client.MBCurrencyRate;
import com.currency.proxy.client.MBCurrencyRatesClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mysite.currency_rate_web_service.Currency;
import org.mysite.currency_rate_web_service.CurrencyRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRatesRepository {
	
	private static final Logger log = LoggerFactory.getLogger(CurrencyRatesRepository.class);
	
	MBCurrencyRatesClient ratesClient;
	
	@Autowired
	public CurrencyRatesRepository(MBCurrencyRatesClient ratesClient) {
		this.ratesClient = ratesClient;
	}
	
	public List<CurrencyRate> getRates(String currencyCode) {
		
		MBCurrencyRate[] mbCurrencyRates = ratesClient.getMBCurrencyRates();
		
		if (mbCurrencyRates.length == 0) {
			return null;
		}
		
		ArrayList<CurrencyRate> currencyRates = new ArrayList<>(mbCurrencyRates.length);
		
		for (MBCurrencyRate mbRate : mbCurrencyRates) {
			
			if (mbRate.getRateBuy() != null && mbRate.getRateSell() != null) {
				
				CurrencyRate rate = new CurrencyRate();
				
				rate.setCurrencyCode(Currency.fromValue(mbRate.getCurrencyCodeA()));
				rate.setRateBuy(mbRate.getRateBuy());
				rate.setRateSell(mbRate.getRateSell());
				rate.setDate(mbRate.getDate());
				
				currencyRates.add(rate);
			}
		}
		
		if (currencyCode == null || currencyCode.isEmpty()) {
			return currencyRates;
		}
		
		Currency currency = Currency.fromValue(currencyCode);
		
		for (CurrencyRate rate : currencyRates) {
			
			if (currency.equals(rate.getCurrencyCode())) {
				return new ArrayList<>(Arrays.asList(rate));
			}
		}
		
		return null;
	}
	
}
