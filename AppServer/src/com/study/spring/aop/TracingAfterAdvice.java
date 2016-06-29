package com.study.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/* 
 * 表示一个在方法返回时进行拦截的Advice 
 */
public class TracingAfterAdvice implements AfterReturningAdvice {
	
	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		System.out.println(method.getDeclaringClass().getName() + "."
				+ method.getName() + "spend time: " + returnValue);
		System.out
				.println("TracingAfterAdvice.afterReturning --> "
						+ method.getDeclaringClass().getName() + "."
						+ method.getName());
	}
}