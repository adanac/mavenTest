package com.adanac.book.thread.test.one;

import org.junit.Test;

public class IsAliveTest {

	@Test
	public void test() {
		MyThread mythread = new MyThread();
		System.out.println("begin ==" + mythread.isAlive());
		mythread.start();
		System.out.println("end ==" + mythread.isAlive());
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("run=" + this.isAlive());
		}
	}
}
