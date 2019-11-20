package com.anupama.ks.consumer.controller;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anupama.ks.consumer.service.influxdb.InfluxDAO;
import com.anupama.ks.consumer.service.kafka.ConsumerCreator;
import com.anupama.ks.consumer.service.legacy.ConsumerRepository;
import com.anupama.ks.consumer.service.legacy.DeviceEntity;
import com.anupama.ks.consumer.service.legacy.FileHandler;

// Pre-requisite : Up kafka, redis and influx service
@RestController
public class ConsumerImpl {
	
	@Autowired
	FileHandler handler;
	
	@Autowired
	ConsumerRepository repsitory;
	
	@Autowired
	InfluxDAO dao;
	
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
		// TO-DO : Need to store proper response
		return consumerRecords.records("demo").toString();
		
	}
	
	@RequestMapping(value = "/data/consumer/influx", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String consumerDataInfluxdb() {	
		
		String kafkaData = consumerDataKafka();
		dao.save(kafkaData);
		
		return "Data Stored sucessfully";
		
	}
	


}
