package com.migmig;

import com.migmig.model.PostalCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@SpringBootApplication
public class CpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpApplication.class, args);
	}
}

