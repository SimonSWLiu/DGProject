package com.study.sync;

/**
 * 继承Thread类，不能资源共享
 * */
class testThread extends Thread {
	
	public void run() {
		for (int i = 0; i < 7; i++) {
			if (count > 0) {
				System.out.println("count= " + count--);
			}
		}
	}

	public static void main(String[] args) {
		testThread h1 = new testThread();
		testThread h2 = new testThread();
		testThread h3 = new testThread();
		h1.start();
		h2.start();
		h3.start();
	}

	private int count = 5;
}