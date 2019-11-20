package com.anupama.ks.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anupama.ks.producer.service.ProducerRepository;

@SpringBootApplication
public class DataProducerApplication {
	
	@Autowired
	ProducerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DataProducerApplication.class, args);
	}

}
