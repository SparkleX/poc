package com.next.odata4;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.next.odata4.config.ODataServiceScan;
import com.querydsl.core.types.Predicate;

import gen.table.BmoOCRD;
import gen.table.BmoORDR;

@SpringBootApplication
//@ServletComponentScan
@EntityScan(basePackageClasses = BmoORDR.class)
//@ODataServiceScan
@RestController
public class AppOData4
{
	@Autowired
	RepoOrder repoOrder;
	@RequestMapping(method = RequestMethod.GET, value = "/users")
	@ResponseBody
	public Iterable<BmoORDR> findAllByWebQuerydsl(
	  @QuerydslPredicate(root = BmoORDR.class) Predicate predicate) {
	    return repoOrder.findAll(predicate);
	}
	public static void main(String[] args)
	{
		ApplicationContext appCtx = SpringApplication.run(AppOData4.class, args);
	}
}
