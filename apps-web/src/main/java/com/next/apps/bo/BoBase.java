package com.next.apps.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;

public class BoBase<T_Bean, T_Repo extends QuerydslPredicateExecutor<T_Bean>> 
{
	@Autowired
	T_Repo repo;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	@ResponseBody
	protected Iterable<T_Bean> search(@QuerydslPredicate Predicate predicate) {
	    return repo.findAll(predicate);
	}
}
