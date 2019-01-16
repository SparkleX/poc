package com.next.odata4.jpa.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;

import com.next.odata4.annotation.ODataEntitySet;
import com.next.odata4.jpa.model.MdEntitySet;
/*
public class EntitySetsCreator {

	public List<ODataEntitySet> getEntitySets(EntityManagerFactory emf) 
	{
		List<ODataEntitySet> rt = new ArrayList<ODataEntitySet>();
		for( EntityType<?> e:emf.getMetamodel().getEntities())
		{
			System.out.println(e.getJavaType());
			ODataEntitySet eSet = getEntitySet(e);
			if(eSet==null)
			{
				continue;
			}
			rt.add(eSet);

		}
		return rt;
	}
	public static final String NAMESPACE = "OData.Demo";
	private ODataEntitySet getEntitySet(EntityType<?> e) 
	{
		Class<?> javaType = e.getJavaType();
		ODataEntitySets aEntitySets = javaType.getAnnotation(ODataEntitySets.class);
		if(aEntitySets==null) return null;
		ODataEntitySet entitySet = new ODataEntitySet();
		entitySet.getEntitySet().setName(aEntitySets.name());
		
		
		FullQualifiedName type = new FullQualifiedName(NAMESPACE, aEntitySets.type());
		entitySet.getEntitySet().setType(type);
		entitySet.setJavaType(javaType);
		return entitySet;
	}

}*/
