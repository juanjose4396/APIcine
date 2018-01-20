package com.orussystem.response;

import org.springframework.stereotype.Component;

import com.orussystem.dto.DataResponseAvailability;

@Component
public class ResponseControllerAvailability {
	private DataResponseAvailability data;

	public DataResponseAvailability getData() {
		return data;
	}

	public void setData(DataResponseAvailability data) {
		this.data = data;
	}
	
}
