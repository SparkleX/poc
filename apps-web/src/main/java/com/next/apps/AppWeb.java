package com.next.apps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import gen.table.BmoORDR;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan(basePackageClasses = BmoORDR.class)
@EnableSwagger2
public class AppWeb
{
	public static void main(String[] args)
	{
		SpringApplication.run(AppWeb.class, args);
	}
}
