package com.study.sync;
/*
实现Runnable接口比继承Thread类所具有的优势：

1）：适合多个相同的程序代码的线程去处理同一个资源

2）：可以避免java中的单继承的限制

3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立。
*/
class MyThread implements Runnable {

	private int ticket = 5; // 5张票

	public void run() {
		for (int i = 0; i <= 20; i++) {
			if (this.ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "正在卖票"
						+ this.ticket--);
			}
		}
	}
}

public class testRunnable {

	public static void main(String[] args) {
		MyThread my = new MyThread();
		new Thread(my, "1号窗口").start();
		new Thread(my, "2号窗口").start();
		new Thread(my, "3号窗口").start();
	}
}