package com.study.spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloServiceTest {
	@Test
	public void testHelloWorld() {
		// 1、读取配置文件实例化一个IOC容器
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"ioc.xml");
		// 2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
		HelloService helloService = context.getBean("helloService",
				HelloService.class);
		// 3、执行业务逻辑
		helloService.sayHello();
	}
}