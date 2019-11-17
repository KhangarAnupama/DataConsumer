package com.anupama.ks.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anupama.ks.producer.service.DeviceEntity;
import com.anupama.ks.producer.service.FileHandler;
import com.anupama.ks.producer.service.ProducerRepository;
@RestController
public class ProducerImpl {
	
	@Autowired
	FileHandler handler;
	
	@Autowired
	ProducerRepository repsitory;
	
	@RequestMapping(value = "/data/producer", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public DeviceEntity produceData(@RequestParam(value = "mac", required = true) String macAddress) {
		
		DeviceEntity deviceData = null;
		if(handler.isAuthentic(macAddress)) {
			deviceData = handler.readData();
			repsitory.save(deviceData);
		}
		
		return deviceData;
	}

}
