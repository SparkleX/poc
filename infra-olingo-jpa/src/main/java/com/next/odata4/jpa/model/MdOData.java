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
	EntityTypeMap mapNameToEntityType = new EntityTypeMap();
	
	@Autowired
	EntityManagerFactory emf;
	
	public MdEntitySets getEntitySets() {
		return entitySets;
	}



	@Autowired
	MdEntitySets entitySets;
	
	public CsdlEntityType getEntityType(String name) {
		return mapNameToEntityType.get(name);
	}


	public Collection<CsdlEntityType> getEntityTypes() 
	{
		return mapNameToEntityType.values();
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		/*EntitySetsCreator util = new EntitySetsCreator();
		List<ODataEntitySet> sets = util.getEntitySets(emf);
		for(ODataEntitySet o:sets)
		{
			mapNameToEntitySet.put(o.getEntitySet().getName(), o);
		}*/
		
		
		EntityTypeUtil utilType = new EntityTypeUtil();
		for(CsdlEntityType entityType:utilType.getEntityTypes(emf))
		{
			this.mapNameToEntityType.put(entityType.getName(), entityType);
		}
		
	}
}
