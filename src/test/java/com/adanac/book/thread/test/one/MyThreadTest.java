package com.adanac.book.thread.test.one;

import org.junit.Test;

public class MyThreadTest {
	/**
	 * start()方法是启动（即开辟)一个线程的方法，因此线程的启动必须通过此方法， 
	 * 而run()方法，只是Thread类的一个方法，它本身并不能开辟线程。
	 */

	@Test
	public void test1() {
		MyThread mythread = new MyThread();
		mythread.start();
		System.out.println("运行结束！");
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			super.run();
			System.out.println("MyThread");
		}
	}

}
