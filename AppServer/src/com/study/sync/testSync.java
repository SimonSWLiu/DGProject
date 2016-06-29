package com.study.sync;

/**
 * 继承Thread类，不能资源共享
 **/
class testSync {

	public static void main(String[] args) {

		Thread t = new Thread("thread1") {

			public void run() {
				for (int i = 1; i <= 10; i += 2) {

					try {
						System.out.println(Thread.currentThread().getName()
								+ " --> " + i);
						sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		Thread t2 = new Thread("thread2") {

			public void run() {
				for (int i = 2; i <= 10; i += 2) {

					try {
						System.out.println(Thread.currentThread().getName()
								+ " --> " + i);
						sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		t.start();
		t2.start();

	}
}