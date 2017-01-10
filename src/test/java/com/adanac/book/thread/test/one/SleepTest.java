package com.adanac.book.thread.test.one;

import org.junit.Test;

public class SleepTest {

	@Test
	public void test_1() {
		MyThread1 mythread = new MyThread1();
		System.out.println("begin =" + System.currentTimeMillis());
		mythread.run();
		System.out.println("end   =" + System.currentTimeMillis());
	}

	@Test
	public void test_2() {
		MyThread2 mythread = new MyThread2();
		System.out.println("begin =" + System.currentTimeMillis());
		mythread.start();
		System.out.println("end   =" + System.currentTimeMillis());
	}

	class MyThread1 extends Thread {
		@Override
		public void run() {
			try {
				System.out.println("run threadName=" + this.currentThread().getName() + " begin");
				Thread.sleep(2000);
				System.out.println("run threadName=" + this.currentThread().getName() + " end");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class MyThread2 extends Thread {
		@Override
		public void run() {
			try {
				System.out.println(
						"run threadName=" + this.currentThread().getName() + " begin =" + System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println(
						"run threadName=" + this.currentThread().getName() + " end   =" + System.currentTimeMillis());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
