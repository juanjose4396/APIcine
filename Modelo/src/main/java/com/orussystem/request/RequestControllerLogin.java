package com.orussystem.request;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.orussystem.dto.DataRequestLogin;

@Component
public class RequestControllerLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DataRequestLogin data;

	public DataRequestLogin getData() {
		return data;
	}

	public void setData(DataRequestLogin data) {
		this.data = data;
	}
	
}