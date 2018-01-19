package com.orussystem.response;

import org.springframework.stereotype.Component;

import com.orussystem.dto.DataResponsePelicula;

@Component
public class ResponseControllerPelicula {
	private DataResponsePelicula data;

	public DataResponsePelicula getData() {
		return data;
	}

	public void setData(DataResponsePelicula data) {
		this.data = data;
	}
	
	
}
