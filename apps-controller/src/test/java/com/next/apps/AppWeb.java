package com.next.apps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import gen.table.BmoORDR;

@SpringBootApplication
@EntityScan(basePackageClasses = BmoORDR.class)
public class AppWeb
{
	public static void main(String[] args)
	{
		SpringApplication.run(AppWeb.class, args);
	}
}
