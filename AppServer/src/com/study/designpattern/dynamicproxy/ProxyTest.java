package com.study.designpattern.dynamicproxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class ProxyTest {

	@Test
	public void test() {
		Laptop laptop = new Laptop();
		TimeHander hander = new TimeHander(laptop);
		IComputer computer = (IComputer) Proxy.newProxyInstance(laptop
				.getClass().getClassLoader(),
				laptop.getClass().getInterfaces(), hander);
		computer.execute();
	}

}