package com.currency.proxy.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyCodeDeserializer extends JsonDeserializer<String> {
	
	Logger logger = LoggerFactory.getLogger(CurrencyCodeDeserializer.class);
	
	private static final Properties currencyCodes = new Properties();
	
	private synchronized void loadCurrencyList() {
		try {
			this.currencyCodes.load(getClass().getResourceAsStream("/CurrencyCodes.txt"));
			logger.info("Currencies list successfully loaded from file");
		} catch (FileNotFoundException e) {
			logger.error("File CurrencyCodes.txt not found", e);
			return;
		} catch (IOException e) {
			logger.error("Error reading CurrencyCodes.txt", e);
			return;
		}
	}
	
	@Override
	public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		
		if (currencyCodes.isEmpty()) {
			loadCurrencyList();
		}
		
		String numericCode = jp.getText().trim();
		
		// Padding numeric currency codes with 0 (zeroes) up to 3 positions
		if (numericCode.length() < 3) {
			numericCode = new String(new char[3 - numericCode.length()]).replace("\0", "0") + numericCode;
		}
		
		return currencyCodes.getProperty(numericCode) != null ? currencyCodes.getProperty(numericCode) : numericCode;
	}
	
}
