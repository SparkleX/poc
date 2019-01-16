package com.next.odata4;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;

import com.next.odata4.config.ODataServiceScan;

import gen.table.BmoORDR;

@SpringBootApplication
@ServletComponentScan
@EntityScan(basePackageClasses = BmoORDR.class)
@ODataServiceScan
public class AppOData4
{

	public static void main(String[] args)
	{
		ApplicationContext appCtx = SpringApplication.run(AppOData4.class, args);
	}
}
