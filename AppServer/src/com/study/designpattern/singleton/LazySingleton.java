package com.study.designpattern.singleton;

/**
 * 一、懒汉，常用的写法
 * 多线程不安全
 */
class LazySingleton {
	private static LazySingleton singleton;

	private LazySingleton() {
	}

	public static LazySingleton getInstance() {
	//public synchronized static LazySingleton getInstance() //此优化尽管解决了线程问题，但性能大大降低了
		if (singleton == null) {
			singleton = new LazySingleton();
		}
		return singleton;
	}
}