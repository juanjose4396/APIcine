package com.orussystem.request;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.orussystem.dto.DataRequestCompra;

@Component
public class RequestControllerCompra implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DataRequestCompra data;

	public DataRequestCompra getData() {
		return data;
	}

	public void setData(DataRequestCompra data) {
		this.data = data;
	}

}