package com.study.demo;

public class TestCharacter {
	final static int time = 50000; // 循环次数

	public TestCharacter() {

	}

	public void test(String s) {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			s += "add";
		}
		long over = System.currentTimeMillis();
		System.out.println("操作" + s.getClass().getName() + "类型使用的时间为："
				+ (over - begin) + "毫秒");
	}

	public void test(StringBuffer s) {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			s.append("add");
		}
		long over = System.currentTimeMillis();
		System.out.println("操作" + s.getClass().getCanonicalName() + "类型使用的时间为："
				+ (over - begin) + "毫秒");
	}

	public void test(StringBuilder s) {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			s.append("add");
		}
		long over = System.currentTimeMillis();
		System.out.println("操作" + s.getClass().getName() + "类型使用的时间为："
				+ (over - begin) + "毫秒");
	}

	/* 对 String 直接进行字符串拼接的测试 */
	public void test2() {
		String s2 = "abcd";
		long begin = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			String s = s2 + s2 + s2;
		}
		long over = System.currentTimeMillis();
		System.out.println("操作字符串对象引用相加类型使用的时间为：" + (over - begin) + "毫秒");
	}

	public void test3() {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			String s = "abcd" + "abcd" + "abcd";
		}
		long over = System.currentTimeMillis();
		System.out.println("操作字符串相加使用的时间为：" + (over - begin) + "毫秒");
	}

	public static void main(String[] args) {
		String s1 = "abcd";
		StringBuffer st1 = new StringBuffer("abcd");
		StringBuilder st2 = new StringBuilder("abcd");
		TestCharacter tc = new TestCharacter();
		tc.test(s1);
		tc.test(st1);
		tc.test(st2);
		tc.test2();
		tc.test3();
	}
}