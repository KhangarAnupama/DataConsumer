package com.anupama.ks.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anupama.ks.consumer.service.ConsumerRepository;

@SpringBootApplication
public class DataConsumerApplication {
	
	@Autowired
	ConsumerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DataConsumerApplication.class, args);
	}

}
