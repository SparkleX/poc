package com.next.odata4.jpa.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class MemberUtil {

	public static <T extends Annotation> T getAnnotation(Member member, Class<T> annotationClass) {
		if (member instanceof Field) {
			return ((Field) member).getAnnotation(annotationClass);
		}
		if (member instanceof Method) {
			return ((Method) member).getAnnotation(annotationClass);
		}
		throw new RuntimeException();
	}

}
