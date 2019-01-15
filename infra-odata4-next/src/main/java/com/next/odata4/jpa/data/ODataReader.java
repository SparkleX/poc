package com.next.odata4.jpa.data;

import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Type;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;

public class ODataReader 
{
	JpaDataReader jpaReader = new JpaDataReader();
	DataWriter dataWriter = new DataWriter();
	public Entity read(EntityManagerFactory emf, EntityManager em, Class<?> clazz, String key) throws ODataApplicationException 
	{
		ManagedType<?> managedType = emf.getMetamodel().managedType(clazz);
		EntityType<?> entityType = (EntityType<?>) managedType;
		Type<?> type = entityType.getIdType();
		
		Object oKey = ConvertUtils.convert(key, type.getJavaType());
		
		Object data = jpaReader.readOne(em, clazz, oKey);
		if(data==null)
		{
			return null;
		}
		Entity rt = dataWriter.write(data);
		return rt;
	}
	

}
