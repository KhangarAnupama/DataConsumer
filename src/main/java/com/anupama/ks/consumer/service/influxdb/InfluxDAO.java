package com.anupama.ks.consumer.service.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.impl.InfluxDBMapper;
import org.springframework.stereotype.Service;

@Service
public class InfluxDAO {
	
	String registerMac = "00:0a:95:9d:68:16";
	
	public void save(String kafkaData) {
		
		InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
		influxDB.createDatabase("kidsTracko");
		
	    InfluxDBMapper influxDBMapper = new InfluxDBMapper(influxDB);
	    
	    DeviceLocationEntity entity = new DeviceLocationEntity();
	    entity.setMac_id(registerMac);
	    entity.setGeoLocation("(1,1)");
	    influxDBMapper.save(entity);
	}

}
