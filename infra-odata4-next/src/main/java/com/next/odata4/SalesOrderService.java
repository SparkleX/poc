package com.next.odata4;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.EntityManager;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.next.odata4.annotation.ODataEntitySet;
import com.next.odata4.config.ODataCrudService;

import gen.table.BmoORDR;

@Service
@Transactional
@ODataEntitySet(name = "ORDRCollection")
public class SalesOrderService implements ODataCrudService<BmoORDR, Integer>
{
	@Autowired
	EntityManager em;
	@Override
	public BmoORDR create(BmoORDR o)
	{
		em.persist(o);
		
		return o;
	}
	
	@Override
	public void update(Integer id, BmoORDR o)
	{
		try 
		{
			BmoORDR oOrgn = em.find(BmoORDR.class, id);
			BeanUtils.copyProperties(oOrgn, o);
			oOrgn.setId(id);
			em.persist(oOrgn);
		} catch (IllegalAccessException | InvocationTargetException e) 
		{
			throw new RuntimeException(e);
		}		
	}
	
	
	@Override
	public void delete(Integer id)
	{
		BmoORDR o = em.find(BmoORDR.class, id);
		em.remove(o);
	}
}
