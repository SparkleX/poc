package com.next.odata4.jpa.model;

import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;

public class ODataEntitySet 
{
	public ODataEntitySet()
	{
		entitySet = new CsdlEntitySet ();	
	}
	public CsdlEntitySet getEntitySet() {
		return entitySet;
	}

	public void setEntitySet(CsdlEntitySet entitySet) {
		this.entitySet = entitySet;
	}

	CsdlEntitySet entitySet;
	public Class<?> getJavaType() {
		return javaType;
	}
	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}

	Class<?> javaType;
}
