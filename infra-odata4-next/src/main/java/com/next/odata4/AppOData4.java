package com.next.odata4;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.odata4.config.ODataServiceScan;

import gen.table.BmoORDR;

@SpringBootApplication
@ServletComponentScan
@EntityScan(basePackageClasses = BmoORDR.class)
@ODataServiceScan
@RestController
public class AppOData4
{

	public static void main(String[] args)
	{
		ApplicationContext appCtx = SpringApplication.run(AppOData4.class, args);
		Object o =  appCtx.getBean("com.next.odata4.OData4Controller");
		System.out.println(o);
	}
	
	@Autowired
	RepoOrder repoOrder;
	@RequestMapping("/test1")
	List<BmoORDR> test()
	{
		repoOrder.deleteById(1);
		return repoOrder.findByMyId(new Integer(1));
	}
}
