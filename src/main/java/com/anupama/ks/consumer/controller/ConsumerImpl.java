package com.anupama.ks.consumer.controller;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anupama.ks.consumer.service.DeviceEntity;
import com.anupama.ks.consumer.service.FileHandler;
import com.anupama.ks.consumer.service.IKafkaConstants;
import com.anupama.ks.consumer.service.ConsumerCreator;
import com.anupama.ks.consumer.service.ConsumerRepository;
@RestController
public class ConsumerImpl {
	
	@Autowired
	FileHandler handler;
	
	@Autowired
	ConsumerRepository repsitory;
	
	@RequestMapping(value = "/data/consumer/file", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public DeviceEntity consumerDataFile(@RequestParam(value = "mac", required = true) String macAddress) {
		
		DeviceEntity deviceData = null;
		if(handler.isAuthentic(macAddress)) {
			deviceData = handler.readData();
			repsitory.save(deviceData);
		}
		
		return deviceData;
	}
	
	@RequestMapping(value = "/data/consumer/kafka", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
//	public String consumerDataKafka(@RequestParam(value = "mac", required = true) String macAddress) {
	public String consumerDataKafka() {	
		Consumer<String, String> consumer = ConsumerCreator.createConsumer();
		
		final ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
		
		consumerRecords.forEach(record -> {
			System.out.println("Record Key " + record.key());
			System.out.println("Record value " + record.value());
			System.out.println("Record partition " + record.partition());
			System.out.println("Record offset " + record.offset());
			
		});
		consumer.commitAsync();
		
		return consumerRecords.records("demo").toString();
		
		
	}
	


}
