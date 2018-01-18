package com.framework.contex;

import org.springframework.context.ApplicationContext;

/**
 * Clase para guardar el contexto de Spring
 * @author ing Diego Barrera
 */
public class SpringContex {

	private static ApplicationContext context;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}
}
