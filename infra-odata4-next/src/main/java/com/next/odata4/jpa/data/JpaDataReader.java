package com.next.odata4.jpa.data;

import javax.persistence.EntityManager;

public class JpaDataReader 
{
	public Object readOne(EntityManager em, Class<?> clazz, Object key)
	{
		return em.find(clazz, key);
	}
}
