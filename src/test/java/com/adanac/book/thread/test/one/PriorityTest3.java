package com.adanac.book.thread.test.one;

import java.util.Random;

import org.junit.Test;

public class PriorityTest3 {

	/**
	 * 
	 * 结论：线程优先级与打印顺序无关
	 */
	@Test
	public void test() {
		for (int i = 0; i < 5; i++) {
			MyThread1 thread1 = new MyThread1();
			thread1.setPriority(5);
			thread1.start();

			MyThread2 thread2 = new MyThread2();
			thread2.setPriority(6);
			thread2.start();
		}
	}

	class MyThread2 extends Thread {
		@Override
		public void run() {
			long beginTime = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++) {
				Random random = new Random();
				random.nextInt();
			}
			long endTime = System.currentTimeMillis();
			System.out.println("☆☆☆☆☆thread 2 use time=" + (endTime - beginTime));
		}
	}

	class MyThread1 extends Thread {
		@Override
		public void run() {
			long beginTime = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++) {
				Random random = new Random();
				random.nextInt();
			}
			long endTime = System.currentTimeMillis();
			System.out.println("★★★★★thread 1 use time=" + (endTime - beginTime));
		}
	}

}
