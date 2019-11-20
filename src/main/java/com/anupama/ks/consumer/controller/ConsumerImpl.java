package com.anupama.ks.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anupama.ks.consumer.service.DeviceEntity;
import com.anupama.ks.consumer.service.FileHandler;
import com.anupama.ks.consumer.service.ConsumerRepository;
@RestController
public class ConsumerImpl {
	
	@Autowired
	FileHandler handler;
	
	@Autowired
	ConsumerRepository repsitory;
	
	@RequestMapping(value = "/data/consumer/file", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public DeviceEntity consumerData(@RequestParam(value = "mac", required = true) String macAddress) {
		
		DeviceEntity deviceData = null;
		if(handler.isAuthentic(macAddress)) {
			deviceData = handler.readData();
			repsitory.save(deviceData);
		}
		
		return deviceData;
	}

}
