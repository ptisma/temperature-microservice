package com.rasus.lab.temperaturemicroservice;

import com.rasus.lab.temperaturemicroservice.model.Measurement;
import com.rasus.lab.temperaturemicroservice.rest.MeasurementController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TemperatureMicroserviceApplication implements CommandLineRunner {

	@Autowired
	MeasurementController measurementController;

	public static void main(String[] args) {

		SpringApplication.run(TemperatureMicroserviceApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		measurementController.insertReadings();
	}
}
