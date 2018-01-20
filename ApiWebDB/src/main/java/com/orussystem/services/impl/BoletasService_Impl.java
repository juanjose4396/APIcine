package com.orussystem.services.impl;


import com.orussystem.services.interfaz.BoletasService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.framework.contex.SpringContex;
import com.framework.repository.factory.RepositoryFactory;
import com.framework.services.GenericServicesImpl;
import com.orussystem.dto.DataResponseAvailability;
import com.orussystem.modelo.Boletas;
import com.orussystem.modelo.Sillas;
import com.orussystem.repository.dao.BoletasDAO;
import com.orussystem.repository.dao.SillasDAO;
import com.orussystem.repository.utils.RepositoryInstance;
import com.orussystem.response.ResponseControllerAvailability;

@Service("BoletasService_Impl")
public class BoletasService_Impl extends GenericServicesImpl implements BoletasService{
	
	private BoletasDAO BoletasDAO;
	
	public int contador=0;
	
	public char filaActual = 'A';
		
	public BoletasService_Impl() {}
	
	@Autowired
	public BoletasService_Impl( @Qualifier("BoletasDAO_Impl") BoletasDAO genericDAO) {
		super(genericDAO);
		this.BoletasDAO = (BoletasDAO) genericDAO;
	}

	@Override
	public ResponseControllerAvailability getAvailability(Long id, Long numeroBoletas) throws Exception {
		DataResponseAvailability data = (DataResponseAvailability)SpringContex.getApplicationContext().getBean(DataResponseAvailability.class);
		ResponseControllerAvailability responseControllerAvailability = (ResponseControllerAvailability)SpringContex.getApplicationContext().getBean(ResponseControllerAvailability.class);

		List<Boletas> boletas = BoletasDAO.findPelicula(id);
		
		if(boletas==null) {
			data.setCodigoRespuesta("ok");
			data.setMensaje("Todas las sillas disponibles");
			data.setAvailability("true");
			
			responseControllerAvailability.setData(data);
			return responseControllerAvailability;
		}
		
		SillasDAO SillasDAO = (SillasDAO) RepositoryFactory.getFactory().get(RepositoryInstance.SillasDAO);
		
		List<Sillas> sillas = SillasDAO.findFilter(null);
		List<String> sillasOcupadas = obtenerSillasOcupadas(boletas);
		
		sillas.forEach( silla -> {
		
			String ubicacion= silla.getUbicacion();
			if(sillasOcupadas.contains(ubicacion) || filaActual!=ubicacion.charAt(0)) {
				if(contador<numeroBoletas){
					contador=0;
				}
				filaActual = ubicacion.charAt(0);
			}else{
				contador++;
			}
		});
		
		if(contador>=numeroBoletas) {
			data.setCodigoRespuesta("ok");
			data.setMensaje("Sillas disponibles");
			data.setAvailability("true");
			
			responseControllerAvailability.setData(data);
			return responseControllerAvailability;
		}
		
		data.setCodigoRespuesta("ok");
		data.setMensaje("No hay sillas disponibles");
		data.setAvailability("false");
		
		responseControllerAvailability.setData(data);
		return responseControllerAvailability;

	}
	
	public List<String> obtenerSillasOcupadas(List<Boletas> boletas){
		List<String> sillasOcupadas = new ArrayList<>();
		boletas.forEach(boleta -> {
			sillasOcupadas.add(boleta.getSilla_fk().getUbicacion());
		});
		
		return sillasOcupadas;
	}
	

}
