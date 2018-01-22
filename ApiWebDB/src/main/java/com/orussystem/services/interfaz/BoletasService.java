package com.orussystem.services.interfaz;

import java.util.Date;
import java.util.List;

import com.framework.services.GenericServices;
import com.orussystem.response.ResponseControllerAvailability;
import com.orussystem.response.ResponseControllerDetalleVentas;
import com.orussystem.response.ResponseControllerLogin;

/**
 * Interfaz que expone los metodos a implementar por el servicio correspondiente
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
@SuppressWarnings("rawtypes")
public interface BoletasService extends GenericServices{
	/**
	 * Metodo que se encarga de verificar que segun el numero de boletas que el usuario desea comprar las sillas de la
	 * pelicula se encuentren juntas y disponibles
	 * @param id identificados de la pelicula deseada
	 * @param numeroBoletas numero de boletas que el usuario desea comprar
	 * @param tipo tipo de funcion de la pelicula (2D o 3D)
	 * @return devuelve un response informado un estado de respuesta y si hay sillas o no disponibles
	 * @throws Exception
	 */
	public ResponseControllerAvailability getAvailability(Long id,Long numeroBoletas,String tipo) throws Exception;
	/**
	 * Metodo que se encarga de crear las boletas compradas por el usuario en la base de datos con la
	 * informacion requerida
	 * @param idUsuario el usuario que realiza la compra de boletas
	 * @param idPelicula la pelicula a la cual corresponde la compra
	 * @param silla la silla correspondiente a la boleta
	 * @param tipo el tipo de funcion de la pelicula (2D o 3D)
	 * @param fecha la fecha de la compra
	 * @return devuelve un response informado sobre el estado exitoso o no de la compra de boletas
	 * @throws Exception
	 */
	public ResponseControllerLogin crearBoletas(Long idUsuario, Long idPelicula,List<Long> silla,String tipo,Date fecha) throws Exception;
	/**
	 * Metodo que se encarga de listar por fecha el detalle de las ventas realizadas en ella,
	 * @return devuelve una data correspondiente a la fecha y los detalles de venta dentro de ella
	 * @throws Exception
	 */
	public ResponseControllerDetalleVentas detalleVentas() throws Exception;
}
