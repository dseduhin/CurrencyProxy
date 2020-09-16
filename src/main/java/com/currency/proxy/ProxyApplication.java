package com.currency.proxy;

import com.currency.proxy.client.MBCurrencyRatesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProxyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}
	
}
