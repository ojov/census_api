package com.ojo.population_project;

import com.ojo.population_project.services.CityService;
import com.ojo.population_project.services.impl.CityServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class PopulationProjectApplication {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		CityServiceImpl cityService = new CityServiceImpl();
		cityService.getTopCities(10);


	SpringApplication.run(PopulationProjectApplication.class, args);
	}

}
