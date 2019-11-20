package com.anupama.ks.consumer.service.legacy;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="geo_data")
public class DeviceEntity implements Serializable {

	private static final long serialVersionUID = 2628705404314017597L;
	
	@Id
	@Column(name = "MAC_ID", unique = true, nullable = false, length = 50)	
	private String macAddress;
	
	@Column(name = "DATA", unique = false, nullable = false, length = 50)	
	private String data;
	
	@Column(name = "Time", unique = true, nullable = false, length = 50)	
	private Timestamp time;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "DeviceEntity [data=" + data + ", macAddress=" + macAddress + ", time=" + time + "]";
	}
	
}
