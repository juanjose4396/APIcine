package com.orussystem.services.impl;


import com.orussystem.services.interfaz.PeliculasService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.framework.contex.SpringContex;
import com.framework.repository.factory.RepositoryFactory;
import com.framework.services.GenericServicesImpl;
import com.orussystem.dto.DataResponseLogin;
import com.orussystem.dto.DataResponsePelicula;
import com.orussystem.dto.DataResponsePeliculas;
import com.orussystem.dto.DataResponseSillas;
import com.orussystem.modelo.Peliculas;
import com.orussystem.modelo.Sillas;
import com.orussystem.repository.dao.PeliculasDAO;
import com.orussystem.repository.dao.SillasDAO;
import com.orussystem.repository.utils.RepositoryInstance;
import com.orussystem.response.ResponseControllerLogin;
import com.orussystem.response.ResponseControllerPelicula;
import com.orussystem.response.ResponseControllerPeliculas;
import com.orussystem.response.ResponseControllerSillas;

@Service("PeliculasService_Impl")
public class PeliculasService_Impl extends GenericServicesImpl implements PeliculasService{
	
	private PeliculasDAO PeliculasDAO;
	
	public PeliculasService_Impl() {}
	
	@Autowired
	public PeliculasService_Impl( @Qualifier("PeliculasDAO_Impl") PeliculasDAO genericDAO) {
		super(genericDAO);
		this.PeliculasDAO = (PeliculasDAO) genericDAO;
	}

	@Override
	public ResponseControllerPeliculas listAll() throws Exception {
		List<Peliculas>peliculas = PeliculasDAO.findFilter(null);
		
		if(peliculas.isEmpty()) {
			throw new Exception("No existen peliculas");
		}
		
		DataResponsePeliculas data = (DataResponsePeliculas)SpringContex.getApplicationContext().getBean(DataResponsePeliculas.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Operacion exitosa");
		data.setPeliculas(peliculas);
		
		ResponseControllerPeliculas responseControllerPeliculas = (ResponseControllerPeliculas)SpringContex.getApplicationContext().getBean(ResponseControllerPeliculas.class);
		responseControllerPeliculas.setData(data);
		
		return responseControllerPeliculas;
	}

	@Override
	public ResponseControllerPelicula getPelicula(Long id) throws Exception {
		
		Peliculas pelicula = PeliculasDAO.findById(Peliculas.class, id);
		
		if(pelicula==null) {
			throw new Exception("La pelicula no existe");
		}
		
		DataResponsePelicula data = (DataResponsePelicula)SpringContex.getApplicationContext().getBean(DataResponsePelicula.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Operacion exitosa");
		data.setPelicula(pelicula);
		
		ResponseControllerPelicula responseControllerPelicula = (ResponseControllerPelicula)SpringContex.getApplicationContext().getBean(ResponseControllerPelicula.class);
		responseControllerPelicula.setData(data);
		
		return responseControllerPelicula;
	}

	@Override
	public ResponseControllerSillas getSillas() throws Exception {
		
		SillasDAO SillasDAO = (SillasDAO) RepositoryFactory.getFactory().get(RepositoryInstance.SillasDAO);
		
		List<Sillas> sillas = SillasDAO.findFilter(null);
		
		DataResponseSillas data = (DataResponseSillas)SpringContex.getApplicationContext().getBean(DataResponseSillas.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Operacion exitosa");
		data.setSillas(sillas);
		
		
		ResponseControllerSillas responseControllerSillas = (ResponseControllerSillas)SpringContex.getApplicationContext().getBean(ResponseControllerSillas.class);
		responseControllerSillas.setData(data);
		
		return responseControllerSillas;
	}
	
}
