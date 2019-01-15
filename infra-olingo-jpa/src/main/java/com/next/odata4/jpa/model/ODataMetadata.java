package com.next.odata4.jpa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;

import com.next.odata4.jpa.utils.EntitySetsCreator;
import com.next.odata4.jpa.utils.EntityTypeUtil;

public class ODataMetadata 
{
	EntitySetsMap mapNameToEntitySet = new EntitySetsMap();
	EntityTypeMap mapNameToEntityType = new EntityTypeMap(); 
	
	public ODataMetadata(EntityManagerFactory emf)
	{
		mapNameToEntitySet = new EntitySetsMap();
		mapNameToEntityType = new EntityTypeMap(); 
		
		EntitySetsCreator util = new EntitySetsCreator();
		List<ODataEntitySet> sets = util.getEntitySets(emf);
		for(ODataEntitySet o:sets)
		{
			mapNameToEntitySet.put(o.getEntitySet().getName(), o);
		}
		
		
		EntityTypeUtil utilType = new EntityTypeUtil();
		for(CsdlEntityType entityType:utilType.getEntityTypes(emf))
		{
			this.mapNameToEntityType.put(entityType.getName(), entityType);
		}
		
	}

	public CsdlEntityType getEntityType(String name) {
		return mapNameToEntityType.get(name);
	}

	public ODataEntitySet getEntitySet(String name) {
		return mapNameToEntitySet.get(name);
	}

	public Collection<CsdlEntityType> getEntityTypes() 
	{
		return mapNameToEntityType.values();
	}

	public Collection<CsdlEntitySet> getEntitySets() 
	{
		Collection<CsdlEntitySet> rt =new ArrayList<CsdlEntitySet>();
		mapNameToEntitySet.values().forEach(a->rt.add(a.getEntitySet()));
		return rt;
	}
}
