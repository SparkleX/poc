package com.next.odata4.jpa.data;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.springframework.beans.BeanUtils;

import com.next.odata4.annotation.ODataProperty;

public class DataWriter 
{
	public Entity write(Object data)
	{
		Entity rt = new Entity();
		PropertyDescriptor[] props = BeanUtils.getPropertyDescriptors(data.getClass());
		for(PropertyDescriptor prop:props)
		{
			Method readMethod = prop.getReadMethod();
			ODataProperty aODataProp = readMethod.getAnnotation(ODataProperty.class);
			if(aODataProp==null)
			{
				continue;
			}
			Object value;
			try {
				value = readMethod.invoke(data);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
			{
				throw new RuntimeException(e);
			}
			Property odataProp = new Property(null, aODataProp.alias(), ValueType.PRIMITIVE, value);
			rt.addProperty(odataProp);
		}
		return rt;
	}
}
