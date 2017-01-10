package com.adanac.book.thread.test.one;

import org.junit.Test;

public class CountOperateTest extends Thread {
	@Test
	public void test() {
		CountOperateTest c = new CountOperateTest();
		Thread t1 = new Thread(c);
		System.out.println("main begin t1 isAlive=" + t1.isAlive());
		t1.setName("A");
		t1.start();
		System.out.println("main end t1 isAlive=" + t1.isAlive());
	}

	public CountOperateTest() {
		System.out.println("CountOperate---begin");

		System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());

		System.out.println("this.getName()=" + this.getName());
		System.out.println("this.isAlive()=" + this.isAlive());

		System.out.println("CountOperate---end");
	}

	@Override
	public void run() {
		System.out.println("run---begin");

		System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());

		System.out.println("this.getName()=" + this.getName());
		System.out.println("this.isAlive()=" + this.isAlive());

		System.out.println("run---end");
	}

}
