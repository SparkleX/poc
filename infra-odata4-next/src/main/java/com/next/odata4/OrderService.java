package com.next.odata4;

import org.springframework.stereotype.Service;

import com.next.odata4.annotation.ODataService;
import com.next.odata4.config.ODataCrudService;

import gen.table.BmoORDR;

@Service
@ODataService(entityClass=BmoORDR.class)
public class OrderService implements ODataCrudService<BmoORDR, Integer>
{
	@Override
	public void create(BmoORDR o)
	{
		
	}
	
	@Override
	public void update(Integer id, BmoORDR o)
	{
		
	}
	
	@Override
	public void delete(Integer id)
	{
		
	}
}
