package com.study.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

/* 
 * 表示一个异常抛出时进行拦截的Advice 
 */
public class TracingThrowsAdvice implements ThrowsAdvice {

	public void afterThrowing(Method method, Object[] args, Object target,
			Throwable subclass) {
		System.out.println("TracingThrowsAdvice.afterThrowing --> " + subclass);
	}
}