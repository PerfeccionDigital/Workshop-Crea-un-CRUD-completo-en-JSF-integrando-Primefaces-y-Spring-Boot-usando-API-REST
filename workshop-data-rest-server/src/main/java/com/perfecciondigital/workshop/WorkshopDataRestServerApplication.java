package com.perfecciondigital.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.perfecciondigital.workshop.entity.Person;

@SpringBootApplication
public class WorkshopDataRestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopDataRestServerApplication.class, args);
	}
	
	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer(){
	    return RepositoryRestConfigurer.withConfig(config -> {
	        config.exposeIdsFor(Person.class);
	    });
	}

}
