package com.next.odata4.config;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ODataServiceConfig 
{
	@Autowired
	ApplicationContext appContext;
	
	static HashMap<Class,Class> serviceClassMap = new HashMap<Class,Class>(); 
	
	@PostConstruct
	public void postConstruct() throws ClassNotFoundException 
	{
		for(String className:ODataComponentRegisteringPostProcessor.services)
		{
			Class<?> serviceClazz = Class.forName(className);
			Type iFace = getODataCrudServiceInterface(serviceClazz);
			ParameterizedType pType = (ParameterizedType)iFace;
			String entityClassName = pType.getActualTypeArguments()[0].getTypeName();
			Class<?> entityClass = Class.forName(entityClassName);
			serviceClassMap.put(entityClass, serviceClazz);
		}		
	}

	private Type getODataCrudServiceInterface(Class<?> clazz) 
	{
		for(Type type:clazz.getGenericInterfaces())
		{
			ParameterizedType pType = (ParameterizedType)type;
			if(pType.getRawType().equals(ODataCrudService.class))
			{
				return type;
			}
		}
		throw new RuntimeException(String.format("interface ODataCrudService is not implemented by class %s ", clazz.getName()));
	}
	
	
	
}
