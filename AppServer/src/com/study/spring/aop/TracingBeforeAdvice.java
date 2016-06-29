package com.study.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/* 
 * 表示一个在方法执行前进行拦截的一个Advice 
 */
public class TracingBeforeAdvice implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("TracingBeforeAdvice.before --> " + method.getDeclaringClass().getName()
				+ "." + method.getName());
	}
}