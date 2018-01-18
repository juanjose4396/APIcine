package com.framework.factory;

import com.framework.contex.SpringContex;

/**
 * Factory
 * @author ing Diego Barrera
 */
public abstract class Factory {

	/**
	 * Metodo para recuperar Instacias del context de spring
	 * @param class_
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */

	protected Object getClazz(String class_) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (Object) SpringContex.getApplicationContext().getBean(class_);
	}
}
