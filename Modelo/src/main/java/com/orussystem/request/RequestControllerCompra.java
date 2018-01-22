package com.orussystem.request;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import com.orussystem.dto.DataRequestCompra;

/**
 * Clase que representa el bean correspondiente al request que el usuario realiza al servicio utilizado,
 * contiene la data necesaria para el funcionamiento de la logica del servicios 
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

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