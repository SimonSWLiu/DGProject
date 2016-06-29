package com.study.algorithm;

import org.junit.Test;

public class BitCountMethodsTest {
	BitCountMethods bcm = new BitCounts();
	int x = 123;

	@Test
	public final void testNormal() {
		System.out.println(bcm.normal(x));
	}

	@Test
	public final void testQuick() {
		System.out.println(bcm.quick(x));
	}

	@Test
	public final void testStatic4bit() {
		System.out.println(bcm.static4bit(x));
	}

	@Test
	public final void testStatic8bit() {
		System.out.println(bcm.static8bit(x));
	}

	@Test
	public final void testParallel() {
		System.out.println(bcm.parallel(x));
	}

	@Test
	public final void testPerfectness() {
		System.out.println(bcm.perfectness(x));
	}

}