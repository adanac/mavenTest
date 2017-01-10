package com.adanac.book.thread.test.one;

import org.junit.Test;

public class CurrentTheadTest {
	@Test
	public void test_countOperate() {
		CountOperate c = new CountOperate();
		Thread t1 = new Thread(c);
		t1.setName("A");
		t1.start();
	}

	@Test
	public void test_1() {
		System.out.println(Thread.currentThread().getName());
	}

	@Test
	public void test_2() {
		MyThread mythread = new MyThread();
		// mythread.start();
		mythread.run();
	}

	class MyThread extends Thread {

		public MyThread() {
			System.out.println("构造方法的打印：" + Thread.currentThread().getName());
		}

		@Override
		public void run() {
			System.out.println("run方法的打印：" + Thread.currentThread().getName());
		}

	}

	class CountOperate extends Thread {

		public CountOperate() {
			System.out.println("CountOperate---begin");
			System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
			System.out.println("this.getName()=" + this.getName());
			System.out.println("CountOperate---end");
		}

		@Override
		public void run() {
			System.out.println("run---begin");
			System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
			System.out.println("this.getName()=" + this.getName());
			System.out.println("run---end");
		}

	}
}
