package com.next.odata4.jpa.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaDataReader 
{
	public Object readOne(EntityManager em, Class<?> clazz, Object key)
	{
		return em.find(clazz, key);
	}

	public <T> List<T> readAll(EntityManager em, Class<T> clazz) 
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> from = cq.from(clazz);
		
		TypedQuery<T> q = em.createQuery(cq);
		List<T> allitems = q.getResultList();		
		return allitems;
	}
}
