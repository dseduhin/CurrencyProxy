package com.currency.proxy.endpoint;

import com.currency.proxy.repository.CurrencyRatesRepository;
import java.util.List;
import org.mysite.currency_rate_web_service.CurrencyRate;
import org.mysite.currency_rate_web_service.GetCurrencyRate;
import org.mysite.currency_rate_web_service.GetCurrencyRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CurrencyRateEndpoint {
	
	private static final String NAMESPACE_URI = "http://mysite.org/currency-rate-web-service";
	
	private CurrencyRatesRepository ratesRepository;
	
	@Autowired
	public CurrencyRateEndpoint(CurrencyRatesRepository ratesRepository) {
		this.ratesRepository = ratesRepository;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRate")
	@ResponsePayload
	public GetCurrencyRateResponse getCurrencyRates(@RequestPayload GetCurrencyRate request) {
		
		GetCurrencyRateResponse response = new GetCurrencyRateResponse();
		List<CurrencyRate> rates = ratesRepository.getRates(request.getCurrencyCode());
		
		if (rates != null) {
			response.getCurrencyRate().addAll(rates);
		}
		
		return response;
	}
	
}
