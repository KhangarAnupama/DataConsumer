package com.anupama.ks.producer.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.anupama.ks.producer.service.DeviceTO;
import com.anupama.ks.producer.service.FileHandler;


@Controller
public class ProducerImpl {
	
	@RequestMapping(value = "/data/producer", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public DeviceTO produceData(@RequestParam(value = "mac", required = true) String macAddress) {
		FileHandler handler = new FileHandler();
		DeviceTO deviceData = null;
		if(handler.isAuthentic(macAddress)) {
			deviceData = handler.readData();
		}
		return deviceData;
	}

}
