package com.next.apps.bo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.apps.repo.query.RepoORDR;
import com.querydsl.core.types.Predicate;

import gen.table.BmoORDR;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/ordr")
@Api(tags= "Sales Orders")
public class BoORDR extends BoBase<BmoORDR,RepoORDR>
{
	@Override
	protected Iterable<BmoORDR> search(Predicate predicate) {
	    return super.search(predicate);
	}
}
