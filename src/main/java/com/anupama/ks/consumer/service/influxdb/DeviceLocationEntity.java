package com.anupama.ks.consumer.service.influxdb;

import java.util.concurrent.TimeUnit;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "deviceLocation", database = "kidsTracko", timeUnit = TimeUnit.SECONDS)
public class DeviceLocationEntity {
	
    @Column(name = "MAC_ADDRESS")
    private String mac_id;

    @Column(name = "GEO_LOCATION")
    private String geoLocation;

	public String getMac_id() {
		return mac_id;
	}

	public void setMac_id(String mac_id) {
		this.mac_id = mac_id;
	}

	public String getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	@Override
	public String toString() {
		return "DeviceLocationEntity [mac_id=" + mac_id + ", geoLocation=" + geoLocation + "]";
	}
    
}
