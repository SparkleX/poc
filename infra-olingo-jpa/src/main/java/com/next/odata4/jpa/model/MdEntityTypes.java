package com.next.odata4.jpa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;

import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.next.odata4.jpa.utils.EntityTypeUtil;
@Component
public class MdEntityTypes extends ArrayList<MdEntityType> implements InitializingBean 
{
	private static final long serialVersionUID = 5654114420773983414L;
	HashMap<String,MdEntityType> nameMap = new HashMap<String,MdEntityType>();
	
	@Override
	public boolean add(MdEntityType o)
	{
		this.nameMap.put(o.getCsdlEntityType().getName(), o);
		return super.add(o);
	}
	
	@Autowired
	EntityManagerFactory emf;
	
	@Override
	public void afterPropertiesSet() throws Exception 
	{
		EntityTypeUtil utilType = new EntityTypeUtil();
		for(MdEntityType entityType:utilType.getEntityTypes(emf))
		{
			this.add(entityType);
		}
		
	}

	public MdEntityType getByName(String name) {
		return nameMap.get(name);
	}

	public Collection<? extends CsdlEntityType> getCollection() {
		Collection<CsdlEntityType> rt =new ArrayList<CsdlEntityType>();
		this.forEach(a->rt.add(a.getCsdlEntityType()));
		return rt;
	}
}
