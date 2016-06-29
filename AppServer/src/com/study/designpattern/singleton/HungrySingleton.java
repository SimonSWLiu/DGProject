package com.study.designpattern.singleton;

/**
 * 二、恶汉,缺点：没有达到lazy loading的效果
 */
class HungrySingleton {
	private static HungrySingleton singleton = new HungrySingleton();

	private HungrySingleton() {
	}

	public static HungrySingleton getInstance() {
		return singleton;
	}
}