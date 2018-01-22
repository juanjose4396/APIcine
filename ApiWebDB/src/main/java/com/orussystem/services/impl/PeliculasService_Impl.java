package com.orussystem.services.impl;


import com.orussystem.services.interfaz.PeliculasService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.framework.contex.SpringContex;
import com.framework.repository.factory.RepositoryFactory;
import com.framework.services.GenericServicesImpl;
import com.orussystem.dto.DataResponsePelicula;
import com.orussystem.dto.DataResponsePeliculas;
import com.orussystem.dto.DataResponseSillas;
import com.orussystem.modelo.Boletas;
import com.orussystem.modelo.Peliculas;
import com.orussystem.modelo.Sillas;
import com.orussystem.repository.dao.BoletasDAO;
import com.orussystem.repository.dao.PeliculasDAO;
import com.orussystem.repository.dao.SillasDAO;
import com.orussystem.repository.utils.RepositoryInstance;
import com.orussystem.response.ResponseControllerPelicula;
import com.orussystem.response.ResponseControllerPeliculas;
import com.orussystem.response.ResponseControllerSillas;

/**
 * Implementacion de la interfaz correspondiente al servicio de peliculas, representa el servicio a utlizar por un controlador
 * y contiene la logica de los metodos que dicho servicio maneja
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
@Service("PeliculasService_Impl")
public class PeliculasService_Impl extends GenericServicesImpl implements PeliculasService{
	
	//variables de clase
	private PeliculasDAO PeliculasDAO;
	private Double total= 0.0;
	
	/**
	 * Constructor vacio de la clase
	 */
	public PeliculasService_Impl() {}
	
	/**
	 * Constructor encargado de cargar el bean del dao a utilzar
	 * @param genericDAO DAO a utilizar
	 */
	@Autowired
	public PeliculasService_Impl( @Qualifier("PeliculasDAO_Impl") PeliculasDAO genericDAO) {
		super(genericDAO);
		this.PeliculasDAO = (PeliculasDAO) genericDAO;
	}
	/**
	 * Encargado de listar todas las peliculas
	 */
	@Override
	public ResponseControllerPeliculas listAll() throws Exception {
		//Se obtienen las peliculas ordenadas
		List<Peliculas>peliculas = PeliculasDAO.findFilter(null);
		
		//Se maneja error en caso de que no hallan
		if(peliculas.isEmpty()) {
			throw new Exception("No existen peliculas");
		}
		
		//Se cargan los beans de la data y response
		DataResponsePeliculas data = (DataResponsePeliculas)SpringContex.getApplicationContext().getBean(DataResponsePeliculas.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Operacion exitosa");
		data.setPeliculas(peliculas);
		
		ResponseControllerPeliculas responseControllerPeliculas = (ResponseControllerPeliculas)SpringContex.getApplicationContext().getBean(ResponseControllerPeliculas.class);
		responseControllerPeliculas.setData(data);
		
		return responseControllerPeliculas;
	}
	/**
	 * Metodo encargado de devolver la pelicula especificada y calcular el detalle de ventas de ella
	 */
	@Override
	public ResponseControllerPelicula getPelicula(Long id) throws Exception {
		//Se busca la pelicula
		Peliculas pelicula = PeliculasDAO.findById(Peliculas.class, id);
		BoletasDAO BoletasDAO = (BoletasDAO)RepositoryFactory.getFactory().get(RepositoryInstance.BoletasDAO);
		
		if(pelicula==null) {
			throw new Exception("La pelicula no existe");
		}
		//Se obtienen las boletas de esa pelicula
		List<Boletas>boletas = BoletasDAO.findIdPelicula(id);
		
		//Se suma el total ganado en base a las boletas de esa pelicula
		boletas.forEach( boleta -> {
			total+=boleta.getPelicula_fk().getPrecio();
		});
		
		//Se response con los datos requeridos
		DataResponsePelicula data = (DataResponsePelicula)SpringContex.getApplicationContext().getBean(DataResponsePelicula.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Operacion exitosa");
		data.setPelicula(pelicula);
		data.setCantidadPersonas(boletas.size()+"");
		data.setDineroRecaudado(total);
		
		//Se reinician los contadores
		total=0.0;
		
		ResponseControllerPelicula responseControllerPelicula = (ResponseControllerPelicula)SpringContex.getApplicationContext().getBean(ResponseControllerPelicula.class);
		responseControllerPelicula.setData(data);
		
		return responseControllerPelicula;
	}
	/**
	 * Metodo encargado de listar las sillas la pelicula especificada
	 */
	@Override
	public ResponseControllerSillas getSillas(Long id,String tipo) throws Exception {
		//Carga de beans
		SillasDAO SillasDAO = (SillasDAO) RepositoryFactory.getFactory().get(RepositoryInstance.SillasDAO);
		BoletasDAO BoletasDAO = (BoletasDAO) RepositoryFactory.getFactory().get(RepositoryInstance.BoletasDAO);
		List<Boletas> boletas = BoletasDAO.findPelicula(id,tipo);
		
		//Se obtienen las sillas de la DB y las sillas ocupadas
		List<Sillas> sillas = SillasDAO.findFilter(null);
		List<Sillas> sillasOcupadas= obtenerSillasOcupadas(boletas);
		
		//Se response con la informacion obtenida
		DataResponseSillas data = (DataResponseSillas)SpringContex.getApplicationContext().getBean(DataResponseSillas.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Operacion exitosa");
		data.setSillas(sillas);
		data.setSillasOcupadas(sillasOcupadas);
		
		ResponseControllerSillas responseControllerSillas = (ResponseControllerSillas)SpringContex.getApplicationContext().getBean(ResponseControllerSillas.class);
		responseControllerSillas.setData(data);
		
		return responseControllerSillas;
	}
	/**
	 * 
	 * @param boletas boletas de la pelicula especificada
	 * @return devuelve las sillas ocupadas en base a las boletas
	 */
	public List<Sillas> obtenerSillasOcupadas(List<Boletas> boletas){
		List<Sillas> sillasOcupadas = new ArrayList<>();
		boletas.forEach(boleta -> {
			if(sillasOcupadas.contains(boleta.getSilla_fk())==false) {
				sillasOcupadas.add(boleta.getSilla_fk());
			}
		});
		
		return sillasOcupadas;
	}	
}
