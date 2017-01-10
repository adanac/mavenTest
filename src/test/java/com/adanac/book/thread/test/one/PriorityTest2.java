package com.adanac.book.thread.test.one;

import java.util.Random;

import org.junit.Test;

public class PriorityTest2 {

	/**
	 * 
	 * 结论：高优先级的线程总是大部分先执行完
	 */
	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			MyThread1 thread1 = new MyThread1();
			thread1.setPriority(1);
			thread1.run();

			MyThread2 thread2 = new MyThread2();
			thread2.setPriority(10);
			thread2.run();
		}
	}

	class MyThread2 extends Thread {
		@Override
		public void run() {
			long beginTime = System.currentTimeMillis();
			long addResult = 0;
			for (int j = 0; j < 10; j++) {
				for (int i = 0; i < 50000; i++) {
					Random random = new Random();
					random.nextInt();
					addResult = addResult + i;
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("☆☆☆☆☆thread 2 use time=" + (endTime - beginTime));
		}
	}

	class MyThread1 extends Thread {
		@Override
		public void run() {
			long beginTime = System.currentTimeMillis();
			long addResult = 0;
			for (int j = 0; j < 10; j++) {
				for (int i = 0; i < 50000; i++) {
					Random random = new Random();
					random.nextInt();
					addResult = addResult + i;
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("★★★★★thread 1 use time=" + (endTime - beginTime));
		}
	}

}
