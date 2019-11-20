package com.anupama.ks.consumer.service.legacy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import redis.clients.jedis.Jedis;

@Service
public class FileHandler {
	
	String registerMac = "00:0a:95:9d:68:16";
	
	public DeviceEntity readData() {
		String fileName = "/var/dataPoint/geoPoints.txt";
		File file = new File(fileName);
		FileReader fr;
		String line = null;
		DeviceEntity deviceTO = new DeviceEntity();
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
//			while((line = br.readLine()) != null){
//				System.out.println(line);
				
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		deviceTO.setData(line);
		deviceTO.setMacAddress(registerMac);
		deviceTO.setTime(new Timestamp(System.currentTimeMillis()));
		
		saveDataInRedisCache(deviceTO);
		return deviceTO;
	}
	
	public void saveDataInRedisCache(DeviceEntity deviceTO) {
		try {
	      Jedis jedis = new Jedis("localhost"); 
	      jedis.set(deviceTO.getMacAddress(), deviceTO.getData()); 
		}catch (InternalServerError e) {
			e.printStackTrace();
			 System.out.println("Failed to connect redis server sucessfully"); 
		}
	}
	
	public boolean isAuthentic(String macAddress) {
		boolean auth = false;
		if(registerMac.equals(macAddress))
			auth =  true;
		return auth;
	}

}
