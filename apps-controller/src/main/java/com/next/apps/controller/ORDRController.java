package com.next.apps.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.apps.service.ORDRService;
import com.querydsl.core.types.Predicate;

import gen.table.BmoORDR;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/ordr")
@Api(tags= "Sales Orders")
public class ORDRController extends ControllerBase<BmoORDR, ORDRService>
{
	@Override
	protected Iterable<BmoORDR> search(Predicate predicate) {
	    return super.search(predicate);
	}
}
