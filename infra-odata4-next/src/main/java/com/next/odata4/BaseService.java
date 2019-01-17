package com.next.odata4;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.EntityManager;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.next.odata4.config.ODataCrudService;

import gen.table.BmoOCRD;

public class BaseService<T, ID> implements ODataCrudService<T, ID>
{
	@Autowired
	EntityManager em;
	
	@Override
	public T create(T o) {
		em.persist(o);
		
		return o;
	}

	@Override
	public void update(ID id, T o) {
		try 
		{
			T oOrgn = (T) em.find(o.getClass(), id);
			BeanUtils.copyProperties(oOrgn, o);
			BeanUtils.setProperty(oOrgn, "id", id);
			em.persist(oOrgn);
		} catch (IllegalAccessException | InvocationTargetException e) 
		{
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void delete(ID id) {
		
		BmoOCRD o = em.find(BmoOCRD.class, id);
		em.remove(o);

	}

}
