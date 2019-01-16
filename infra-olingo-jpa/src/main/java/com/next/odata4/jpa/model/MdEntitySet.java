package com.next.odata4.jpa.model;

import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.server.api.uri.UriParameter;

import com.next.odata4.config.ODataCrudService;

public class MdEntitySet 
{
	public MdEntitySet()
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
	public Class<?> getIdType() {
		return idType;
	}
	public void setIdType(Class<?> idType) {
		this.idType = idType;
	}

	Class<?> idType;
	Class<? extends ODataCrudService> serviceClass;
	
	public Object createKey(List<UriParameter> keyPredicates) 
	{
		String key = keyPredicates.get(0).getText();
		Object oKey = ConvertUtils.convert(key, idType);
		return oKey;
	}
	public void setServiceClass(Class<? extends ODataCrudService> val) {
		this.serviceClass = val;		
	}
	public Class<? extends ODataCrudService> getServiceClass() {
		return serviceClass;
	}
}
