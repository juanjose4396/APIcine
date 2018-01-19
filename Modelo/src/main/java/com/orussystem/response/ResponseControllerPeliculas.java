package com.orussystem.response;

import org.springframework.stereotype.Component;
import com.orussystem.dto.DataResponsePeliculas;

@Component
public class ResponseControllerPeliculas {
	private DataResponsePeliculas data;

	public DataResponsePeliculas getData() {
		return data;
	}

	public void setData(DataResponsePeliculas data) {
		this.data = data;
	}
	
	
}
