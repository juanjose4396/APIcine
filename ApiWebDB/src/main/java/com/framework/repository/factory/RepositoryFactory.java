package com.framework.repository.factory;

import com.framework.factory.Factory;
import com.orussystem.repository.utils.RepositoryInstance;

/**
 * Factory de DAO
 * @author ing Diego Barrera
 */
public class RepositoryFactory extends Factory{

	private static RepositoryFactory factoryActionButton = new RepositoryFactory();
	
	private RepositoryFactory() {}
	
	public static RepositoryFactory getFactory(){
		return factoryActionButton;
	}

	/**
	 * Metodo encargado de retornar la clase solicitada mediente el RepositoryName
	 * @param repositoryName
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public Object get(RepositoryInstance repositoryName) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return getClazz(repositoryName.getRepositorioDAO());
	}
	
}
