package com.currency.proxy.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MBCurrencyRate {
	
	@JsonDeserialize(using = CurrencyCodeDeserializer.class)
	private String currencyCodeA;
//	@JsonDeserialize(using = CurrencyCodeDeserializer.class)
//	private String currencyCodeB;
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	private String date;
	private String rateBuy;
	private String rateSell;
	
	public String getCurrencyCodeA() {
		return currencyCodeA;
	}
	
//	public String getCurrencyCodeB() {
//		return currencyCodeB;
//	}
	
	public String getDate() {
		return date;
	}
	
	public String getRateBuy() {
		return rateBuy;
	}
	
	public String getRateSell() {
		return rateSell;
	}
	
}
