package com.orussystem.response;

import org.springframework.stereotype.Component;
import com.orussystem.dto.DataResponseSillas;

@Component
public class ResponseControllerSillas {
	private DataResponseSillas data;

	public DataResponseSillas getData() {
		return data;
	}

	public void setData(DataResponseSillas data) {
		this.data = data;
	}

}
