package com.kameleon.task.quoteserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class QuoteServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoteServerApplication.class, args);
	}

}
