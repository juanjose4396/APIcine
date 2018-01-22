package com.orussystem.response;

import org.springframework.stereotype.Component;

import com.orussystem.dto.DataResponseLogin;

/**
 * Clase que representa el bean correspondiente a la respuesta utilizada por un servicio,
 * contiene los dtos representativos a la data requerida a devolver en el servicio
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

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
