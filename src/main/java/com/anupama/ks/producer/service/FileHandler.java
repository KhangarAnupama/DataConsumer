package com.anupama.ks.producer.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
	
	String registerMac = "00:0a:95:9d:68:16";
	
	public DeviceTO readData() {
		String fileName = "/var/dataPoint/geoPoints.txt";
		File file = new File(fileName);
		FileReader fr;
		String line = null;
		DeviceTO deviceTO = new DeviceTO();
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
		deviceTO.setTime(System.currentTimeMillis());
		return deviceTO;
	}
	
	public boolean isAuthentic(String macAddress) {
		boolean auth = false;
		if(registerMac.equals(macAddress))
			auth =  true;
		return auth;
	}

}
