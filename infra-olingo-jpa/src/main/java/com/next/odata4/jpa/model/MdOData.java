package com.next.odata4.jpa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.next.odata4.jpa.utils.EntityTypeUtil;

@Component
public class MdOData implements InitializingBean 
{
	
	
	public static final String NAMESPACE = "OData.Demo";
	@Autowired
	EntityManagerFactory emf;
	
	public MdEntitySets getEntitySets() {
		return entitySets;
	}

	@Autowired
	MdEntitySets entitySets;
	
	public MdEntityTypes getEntityTypes() {
		return entityTypes;
	}


	public void setEntityTypes(MdEntityTypes entityTypes) {
		this.entityTypes = entityTypes;
	}

	@Autowired
	MdEntityTypes entityTypes;
	
	public MdFunctions getFunctions() {
		return functions;
	}


	public void setFunctions(MdFunctions functions) {
		this.functions = functions;
	}

	@Autowired
	MdFunctions functions;
	
	@Override
	public void afterPropertiesSet() throws Exception 
	{
		
		/*EntityTypeUtil utilType = new EntityTypeUtil();
		for(CsdlEntityType entityType:utilType.getEntityTypes(emf))
		{
			this.mapNameToEntityType.put(entityType.getName(), entityType);
		}*/
		
	}
}
