package com.currency.proxy.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnixTimestampDeserializer extends JsonDeserializer<String> {
	
	Logger logger = LoggerFactory.getLogger(UnixTimestampDeserializer.class);
	
	@Override
	public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		
		String timestamp = jp.getText().trim();
		
		try {
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.valueOf(timestamp + "000")));
		} catch (NumberFormatException e) {
			logger.warn("Unable to deserialize timestamp: " + timestamp, e);
			return null;
		}
	}
}