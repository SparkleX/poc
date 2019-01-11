package com.next.infra.odata4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;

import gen.table.BmoORDR;

@SpringBootApplication
@ServletComponentScan
//@EnableJpaRepositories(basePackages = "your.repositories.pakage")
@EntityScan(basePackageClasses = BmoORDR.class)
public class AppOdata4Jpa
{

	public static void main(String[] args)
	{
		SpringApplication.run(AppOdata4Jpa.class, args);
	}
}
