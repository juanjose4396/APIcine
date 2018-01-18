package com.framework.framework.util;

/**
 * Clase que maneja los estados del buffer.
 * @author Ing Diego Barrera Sotelo
 * @Company OrusSystem
 */
public class EstadoBufferConsulta {

	//pendiente para tomar por el core
	public static String Pendiente = "P";
	//En curso tomado por el Core
	public static String EnCurso = "EX";
	//Finalizado el proceso
	public static String Listo = "OK";
	
	//Error en el proceso
	public static String Error_No_Respuesta = "E1";
	public static String Error_Clientes = "EC";
	public static String Error = "ER";
	public static String Error_fatal = "EE";
	
}
