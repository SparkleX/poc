package com.next.odata4.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ODataServiceConfig 
{
	@Autowired
	ApplicationContext appContext;
	
	@PostConstruct
	public void postConstruct() throws ClassNotFoundException 
	{
		for(String className:ODataComponentRegisteringPostProcessor.services)
		{
			Class<?> clazz = Class.forName(className);
			Object instance = appContext.getBean(clazz);
			System.out.println(instance);
		}		
	}
}
