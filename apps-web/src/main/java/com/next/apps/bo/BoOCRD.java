package com.next.apps.bo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.apps.repo.query.RepoOCRD;
import com.querydsl.core.types.Predicate;

import gen.table.BmoOCRD;

@RestController
@RequestMapping("/api/ocrd")
public class BoOCRD extends BoBase<BmoOCRD,RepoOCRD>
{
	@Override
	protected Iterable<BmoOCRD> search(Predicate predicate) {
	    return super.search(predicate);
	}

}
