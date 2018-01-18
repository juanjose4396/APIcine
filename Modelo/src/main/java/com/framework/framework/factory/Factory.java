package com.framework.framework.factory;

import org.apache.log4j.Logger;

/**
 * Factory abstract
 * @author dabs
 *
 */
public abstract class Factory {

	private String PATH;
	
	public Logger LOGGER = Logger.getLogger(Factory.class);
	
	public Factory(String path) {
		PATH = path;
	}
	
	/**
	 * Metodo para crear Instaciona
	 * @param class_
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	protected Object getClass_(String class_) throws Exception{
		return (Object)Class.forName(PATH+"."+class_).newInstance();
	}
	
	/**
	 * Metodo para crear Instaciona
	 * @param class_
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	protected Object getClass_(Class<?> class_) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return (Object)Class.forName(class_.getName()).newInstance();
	}
	
}
