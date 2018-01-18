package com.framework.contex;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Clase para inicalizar el contexto Spring
 * @author ing Diego Barrera
 */
public class SpringContexAware implements ApplicationContextAware {

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		SpringContex.setApplicationContext(ctx);
	}
}
