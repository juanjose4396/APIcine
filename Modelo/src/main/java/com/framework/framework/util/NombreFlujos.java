package com.framework.framework.util;

public enum NombreFlujos {

	Prestamo("prestamo");
	

	private String nombreFlujo;

	private NombreFlujos(String nombreFlujo) {
		this.nombreFlujo = nombreFlujo;
	}

	public String getNombreFlujo() {
		return nombreFlujo;
	}

	
}
