package com.next.odata4.jpa.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.next.odata4.annotation.ODataEntitySet;
import com.next.odata4.annotation.ODataEntityType;
import com.next.odata4.config.ODataComponentRegisteringPostProcessor;
import com.next.odata4.config.ODataCrudService;

@Component
public class MdEntitySets extends ArrayList<MdEntitySet> implements InitializingBean 
{
	private static final long serialVersionUID = -4855223094330473058L;
	HashMap<String,MdEntitySet>  nameMap = new HashMap<String,MdEntitySet>();

	@Autowired
	EntityManagerFactory emf;
	
	@Override
	public boolean add(MdEntitySet o)
	{
		nameMap.put(o.getEntitySet().getName(), o);
		return super.add(o);
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
	public static final String NAMESPACE = "OData.Demo";
	@Override
	public void afterPropertiesSet() throws Exception 
	{
		for(String className:ODataComponentRegisteringPostProcessor.services)
		{
			Class<? extends ODataCrudService> serviceClazz = (Class<? extends ODataCrudService>) Class.forName(className);
			Type iFace = getODataCrudServiceInterface(serviceClazz);
			ParameterizedType pType = (ParameterizedType)iFace;
			String entityClassName = pType.getActualTypeArguments()[0].getTypeName();
			Class<?> entityClass = Class.forName(entityClassName);
			
			ODataEntitySet aEntitySets = serviceClazz.getAnnotation(ODataEntitySet.class);
			
			MdEntitySet entitySet = new MdEntitySet();
			entitySet.setJavaType(entityClass);
			entitySet.setServiceClass(serviceClazz);
			String entityTypeName = entityClass.getSimpleName();
			FullQualifiedName type = new FullQualifiedName(NAMESPACE, entityTypeName);
			entitySet.getEntitySet().setName(aEntitySets.name());
			entitySet.getEntitySet().setType(type);
			ManagedType managedType = emf.getMetamodel().managedType(entityClass);
			IdentifiableType idType = (IdentifiableType) managedType;
			entitySet.setIdType(idType.getIdType().getJavaType());
			
			this.add(entitySet);
			
		}
	}
	public Collection<CsdlEntitySet> getEntitySets() 
	{
		Collection<CsdlEntitySet> rt =new ArrayList<CsdlEntitySet>();
		this.forEach(a->rt.add(a.getEntitySet()));
		return rt;
	}
	public MdEntitySet getByName(String name) 
	{
		return nameMap.get(name);
	}
}
