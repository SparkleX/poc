package com.next.odata4.jpa.model;

import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;

public class MdEntityType
{
	public CsdlEntityType getCsdlEntityType() {
		return csdlEntityType;
	}

	public void setCsdlEntityType(CsdlEntityType csdlEntityType) {
		this.csdlEntityType = csdlEntityType;
	}

	public Class<?> getJavaClass() {
		return javaClass;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	Class<?> javaClass;
	CsdlEntityType csdlEntityType;
	
}
