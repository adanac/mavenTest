package com.adanac.book.thread.test.one;

import org.junit.Test;

public class PriorityTest {

	/**
	 * main thread begin priority=5
	 * main thread end   priority=6
	 * MyThread1 run priority=6
	 * MyThread2 run priority=6
	 * 结论：java中，线程的优先级具有继承性，比如A线程启动B线程，则B线程的优先级与A是一样的。
	 */
	@Test
	public void test() {
		System.out.println("main thread begin priority=" + Thread.currentThread().getPriority());
		Thread.currentThread().setPriority(6);
		System.out.println("main thread end   priority=" + Thread.currentThread().getPriority());
		MyThread1 thread1 = new MyThread1();
		thread1.start();
	}

	class MyThread1 extends Thread {
		@Override
		public void run() {
			System.out.println("MyThread1 run priority=" + this.getPriority());
			MyThread2 thread2 = new MyThread2();
			thread2.start();
		}
	}

	class MyThread2 extends Thread {
		@Override
		public void run() {
			System.out.println("MyThread2 run priority=" + this.getPriority());
		}
	}

}
