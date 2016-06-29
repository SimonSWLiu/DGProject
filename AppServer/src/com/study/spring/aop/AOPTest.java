package com.study.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

	@Test
	public void testAdvice() {
		
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop.xml");
		IBusinessLogic ibl = (IBusinessLogic) ac.getBean("businessLogicBean");
		System.err.println("testAdvice start");
		ibl.foo();
		try {
			ibl.bar();
		} catch (BusinessLogicException e) {
			System.out.println("Caught BusinessLogicException");
		}
		ibl.time();
		System.err.println("testAdvice end");
	}
	
	@Test
	public void testMethodInterceptor() {
		
		System.err.println("testMethodInterceptor start");
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop.xml");
		IBusinessLogic ibl = (IBusinessLogic) ac.getBean("testBean");
		ibl.foo();
//		try {
//			ibl.bar();
//		} catch (BusinessLogicException e) {
//			System.out.println("Caught BusinessLogicException");
//		}
		ibl.time();
		System.err.println("testMethodInterceptor end");
	}
}