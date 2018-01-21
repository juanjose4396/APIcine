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

@Service("PeliculasService_Impl")
public class PeliculasService_Impl extends GenericServicesImpl implements PeliculasService{
	
	private PeliculasDAO PeliculasDAO;
	private Double total= 0.0;
	
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
		BoletasDAO BoletasDAO = (BoletasDAO)RepositoryFactory.getFactory().get(RepositoryInstance.BoletasDAO);
		
		if(pelicula==null) {
			throw new Exception("La pelicula no existe");
		}
		
		List<Boletas>boletas = BoletasDAO.findIdPelicula(id);
		
		boletas.forEach( boleta -> {
			total+=boleta.getPelicula_fk().getPrecio();
		});
		
		DataResponsePelicula data = (DataResponsePelicula)SpringContex.getApplicationContext().getBean(DataResponsePelicula.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Operacion exitosa");
		data.setPelicula(pelicula);
		data.setCantidadPersonas(boletas.size()+"");
		data.setDineroRecaudado(total);
		
		total=0.0;
		
		ResponseControllerPelicula responseControllerPelicula = (ResponseControllerPelicula)SpringContex.getApplicationContext().getBean(ResponseControllerPelicula.class);
		responseControllerPelicula.setData(data);
		
		return responseControllerPelicula;
	}

	@Override
	public ResponseControllerSillas getSillas(Long id,String tipo) throws Exception {
		
		SillasDAO SillasDAO = (SillasDAO) RepositoryFactory.getFactory().get(RepositoryInstance.SillasDAO);
		BoletasDAO BoletasDAO = (BoletasDAO) RepositoryFactory.getFactory().get(RepositoryInstance.BoletasDAO);
		List<Boletas> boletas = BoletasDAO.findPelicula(id,tipo);
		
		List<Sillas> sillas = SillasDAO.findFilter(null);
		List<Sillas> sillasOcupadas= obtenerSillasOcupadas(boletas);
		
		DataResponseSillas data = (DataResponseSillas)SpringContex.getApplicationContext().getBean(DataResponseSillas.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Operacion exitosa");
		data.setSillas(sillas);
		data.setSillasOcupadas(sillasOcupadas);
		
		ResponseControllerSillas responseControllerSillas = (ResponseControllerSillas)SpringContex.getApplicationContext().getBean(ResponseControllerSillas.class);
		responseControllerSillas.setData(data);
		
		return responseControllerSillas;
	}
	
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
