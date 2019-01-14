package com.next.odata4.jpa.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;

import com.next.odata4.annotation.ODataEntitySets;

public class EntitySetsCreator {

	public List<CsdlEntitySet> getEntitySets(EntityManagerFactory emf) 
	{
		List<CsdlEntitySet> rt = new ArrayList<CsdlEntitySet>();
		for( EntityType<?> e:emf.getMetamodel().getEntities())
		{
			System.out.println(e.getJavaType());
			CsdlEntitySet eSet = getEntitySet(e);
			if(eSet==null)
			{
				continue;
			}
			rt.add(eSet);

		}
		return rt;
	}
	public static final String NAMESPACE = "OData.Demo";
	private CsdlEntitySet getEntitySet(EntityType<?> e) 
	{
		Class<?> javaType = e.getJavaType();
		ODataEntitySets aEntitySets = javaType.getAnnotation(ODataEntitySets.class);
		if(aEntitySets==null) return null;
		CsdlEntitySet entitySet = new CsdlEntitySet();
		entitySet.setName(aEntitySets.name());
		
		
		FullQualifiedName type = new FullQualifiedName(NAMESPACE, aEntitySets.type());
		entitySet.setType(type);
		return entitySet;
	}

}
