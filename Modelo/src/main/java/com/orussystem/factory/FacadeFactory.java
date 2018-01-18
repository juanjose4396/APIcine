package com.orussystem.factory;

import java.util.HashMap;
import java.util.Map;

import com.framework.client.facade.GenericFacade;
import com.framework.framework.factory.Factory;

/**
 * Factorya de servicios para los clientes
 * 
 * @author ing Diego Barrera
 *
 */
public class FacadeFactory extends Factory {

	private static FacadeFactory servivesFactory = new FacadeFactory();

	private static final String PATH = "com.orussystem.cliente.facade";

	private Map<String, GenericFacade<?>> contenedor = new HashMap<>();

	private FacadeFactory() {
		super(PATH);
	}

	/**
	 * Recupera la instancia singleton
	 * @return
	 */
	public static FacadeFactory getFactory() {
		return servivesFactory;
	}

	/**
	 * Metodo para obtener instancia con la class
	 * @param services
	 * @return
	 */
	public Object getServices(Class<?> services) throws Exception {
		try {
			if (contenedor.containsKey(services.getName())) {
				
				return contenedor.get(services.getName());
			}
			
			contenedor.put(services.getName(), (GenericFacade<?>) getClass_(services));
			
			return contenedor.get(services.getName());
			
		} catch (Exception e) {
			
			LOGGER.error(e.getMessage(), e);
		}
		
		return null;
	}

	/**
	 * Metodo para recuperar instancia con el nombre en el paquete {PATH}
	 * 
	 * @param services
	 * @return
	 */
	public Object getServices(String services) {
		
		try {
			
			if (contenedor.containsKey(PATH + services)) {
				
				return contenedor.get(PATH + services);
			}
			
			contenedor.put(PATH + services, (GenericFacade<?>) getClass_(services));
			
			return contenedor.get(PATH + services);
			
		} catch (Exception e) {
			
			LOGGER.error(e.getMessage(), e);
		}
		
		return null;
	}

}
