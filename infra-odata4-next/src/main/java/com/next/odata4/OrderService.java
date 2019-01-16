package com.next.odata4;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.next.odata4.annotation.ODataEntitySet;
import com.next.odata4.config.ODataCrudService;

import gen.table.BmoORDR;

@Service
@Transactional
@ODataEntitySet(name = "SalesOrders")
public class OrderService implements ODataCrudService<BmoORDR, Integer>
{
	@Autowired
	EntityManager em;
	@Override
	public void create(BmoORDR o)
	{
		em.persist(o);
	}
	
	@Override
	public void update(Integer id, BmoORDR o)
	{
		em.persist(o);
	}
	
	
	@Override
	public void delete(Integer id)
	{
		BmoORDR o = em.find(BmoORDR.class, id);
		em.remove(o);
	}
}
