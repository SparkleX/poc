package com.next.odata4.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import com.next.odata4.config.ODataServiceScanRegistrar;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ODataServiceScanRegistrar.class,ODataServiceConfig.class})
public @interface ODataServiceScan 
{


	@AliasFor("basePackages")
	String[] value() default {};


	@AliasFor("value")
	String[] basePackages() default {};


	Class<?>[] basePackageClasses() default {};

}