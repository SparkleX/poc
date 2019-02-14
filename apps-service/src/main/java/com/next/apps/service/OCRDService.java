package com.next.apps.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.next.apps.repo.query.RepoOCRD;

import gen.table.BmoOCRD;

@Service
public class OCRDService extends ServiceBase<BmoOCRD,RepoOCRD>
{

	public BigDecimal checkBalance() 
	{
		return BigDecimal.ZERO;
	}

}
