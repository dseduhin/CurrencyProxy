package com.currency.proxy;

import com.currency.proxy.client.MBCurrencyRate;
import com.currency.proxy.client.MBCurrencyRatesClient;
import com.currency.proxy.endpoint.CurrencyRateEndpoint;
import com.currency.proxy.repository.CurrencyRatesRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mysite.currency_rate_web_service.Currency;
import org.mysite.currency_rate_web_service.CurrencyRate;
import org.mysite.currency_rate_web_service.GetCurrencyRate;
import org.mysite.currency_rate_web_service.GetCurrencyRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProxyApplicationTests {
	
	@Autowired
	MBCurrencyRatesClient mbCurrencyRatesClient;
	
	@Autowired
	CurrencyRatesRepository currencyRatesRepository;
	
	@Autowired
	private CurrencyRateEndpoint currencyRateEndpoint;
	
	@Test
	void contextLoads() {
		Assertions.assertNotNull(mbCurrencyRatesClient);
	}
	
	@Test
	void receiveData() {
		MBCurrencyRate[] currencyRates = mbCurrencyRatesClient.getMBCurrencyRates();
		Assertions.assertNotNull(currencyRates);
	}
	
	@Test
	void checkRates() {
		List<CurrencyRate> currencyRates = currencyRatesRepository.getRates(null);
		Assertions.assertNotNull(currencyRates);
		Assertions.assertEquals(5, currencyRates.size());
	}
	
	@Test
	void checkRate() {
		List<CurrencyRate> currencyRates = currencyRatesRepository.getRates("USD");
		Assertions.assertNotNull(currencyRates);
		Assertions.assertEquals(1, currencyRates.size());
		Assertions.assertEquals(Currency.USD, currencyRates.get(0).getCurrencyCode());
	}
	
	@Test
	void checkEndpoint() {
		GetCurrencyRate request = new GetCurrencyRate();
		request.setCurrencyCode(Currency.USD.value());
		
		GetCurrencyRateResponse response = currencyRateEndpoint.getCurrencyRates(request);
		
		Assertions.assertNotNull(response);
		Assertions.assertFalse(response.getCurrencyRate().isEmpty());
		Assertions.assertEquals(Currency.USD, response.getCurrencyRate().get(0).getCurrencyCode());
	}
}
