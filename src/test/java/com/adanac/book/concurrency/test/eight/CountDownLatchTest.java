package com.adanac.book.concurrency.test.eight;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	/**
	 * CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完成，这里就传入N。
	 */
	static CountDownLatch c = new CountDownLatch(2);

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(1);
				c.countDown();// 调用countDown()方法，N减1
				System.out.println(2);
				c.countDown();
			}
		}).start();
		c.await();// CountDownLatch的await方法会阻塞当前线程，直到N变成零
		System.out.println("3");
	}
}
