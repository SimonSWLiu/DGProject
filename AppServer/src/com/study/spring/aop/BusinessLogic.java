package com.study.spring.aop;

public class BusinessLogic implements IBusinessLogic {
	@Override
	public void foo() {
		System.out.println("Inside BusinessLogic.foo()");
	}

	@Override
	public void bar() throws BusinessLogicException {
		System.out.println("Inside BusinessLogic.bar()");
		throw new BusinessLogicException();
	}

	/*
	 * 返回该方法执行的时间
	 */
	@Override
	public long time() {
		System.out.println("Inside BusinessLogic.time()");
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++)
			;
		long endTime = System.currentTimeMillis();

		return (endTime - startTime);
	}
}