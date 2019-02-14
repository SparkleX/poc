package com.next.apps.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.apps.repo.query.RepoORDR;

import gen.table.BmoORDR;

@Service
public class ORDRService extends ServiceBase<BmoORDR,RepoORDR>
{

	@Autowired
	OCRDService svcBP;
	public BigDecimal checkCredit() 
	{
		BigDecimal a = svcBP.checkBalance();
		BigDecimal b = svcBP.checkBalance();
		return a.add(b);
	}

}
