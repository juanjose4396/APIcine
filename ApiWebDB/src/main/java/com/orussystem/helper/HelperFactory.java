package com.orussystem.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Factoria para crear Helper
 * @author dabs
 *
 */
public class HelperFactory {

	private static HelperFactory instance = null;
	
	private static Map<String, HelperAbstract> container = new HashMap<>();
	
	private static Logger LOGGER = Logger.getLogger(HelperAbstract.class);
	
	private HelperFactory(){
	}
	
	public static HelperFactory getInstans(){
		if(instance==null){
			instance = new HelperFactory();
		}
		return instance;
	}
	
	
	/**
	 * Metodo para construir helper
	 * @param nameHelper
	 * @return
	 */
	public HelperAbstract getHelper(Class nameHelper) {
		
		String paquete = nameHelper.getName();
		
		try {
			if(container.containsKey(paquete)){
				return container.get(paquete);
			}
			HelperAbstract helperAbstract = (HelperAbstract)  Class.forName(paquete).newInstance();
			container.put(paquete, helperAbstract);
			return helperAbstract;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		} 
		
		
		return null;
	}

	
}
