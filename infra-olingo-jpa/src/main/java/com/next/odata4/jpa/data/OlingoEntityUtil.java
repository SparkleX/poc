package com.next.odata4.jpa.data;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.springframework.beans.BeanUtils;

import com.next.odata4.annotation.ODataProperty;

public class OlingoEntityUtil {
	static public <T> T read(Entity data, Class<T> clazz)
	{
		try
		{
			T rt = clazz.newInstance();
			for(Property prop:data.getProperties())
			{
				String name = prop.getName();
				Object value =prop.getValue();
				if(value==null) continue;
				PropertyDescriptor propDesc = BeanUtils.getPropertyDescriptor(clazz, name);
				Class targetType = propDesc.getPropertyType();
				Object targetValue = ConvertUtils.convert(value, targetType);
				propDesc.getWriteMethod().invoke(rt, targetValue);
			}
			return rt;
		}catch(Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}

	static public Entity write(Object data) {
		Entity rt = new Entity();
		PropertyDescriptor[] props = BeanUtils.getPropertyDescriptors(data.getClass());
		for (PropertyDescriptor prop : props) {
			Method readMethod = prop.getReadMethod();
			ODataProperty aODataProp = readMethod.getAnnotation(ODataProperty.class);
			if (aODataProp == null) {
				continue;
			}
			Object value;
			try {
				value = readMethod.invoke(data);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			Property odataProp = new Property(null, prop.getName(), ValueType.PRIMITIVE, value);
			rt.addProperty(odataProp);
		}
		return rt;
	}
}
