package com.orussystem.response;

import org.springframework.stereotype.Component;

import com.orussystem.dto.DataResponseLogin;

@Component
public class ResponseControllerLogin {
	private DataResponseLogin data;

	public DataResponseLogin getData() {
		return data;
	}

	public void setData(DataResponseLogin data) {
		this.data = data;
	}
	
	
}
