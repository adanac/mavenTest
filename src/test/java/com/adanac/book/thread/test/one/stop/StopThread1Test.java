package com.adanac.book.thread.test.one.stop;

import org.junit.Test;

public class StopThread1Test {
	@Test
	public void test3() {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(1000);
			thread.interrupt();
			System.out.println("是否停止1？=" + thread.isInterrupted());// false
			System.out.println("是否停止2？=" + thread.isInterrupted());// false
		} catch (InterruptedException e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end!");
	}

	@Test
	public void test2() {
		Thread.currentThread().interrupt();
		System.out.println("是否停止1？=" + Thread.interrupted());// true，主线程已中断
		System.out.println("是否停止2？=" + Thread.interrupted());// false，第一次调用
																// 已清除了中断状态
		System.out.println("end!");
	}

	@Test
	public void test1() {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(1000);
			thread.interrupt();
			// Thread.currentThread().interrupt();
			System.out.println("是否停止1？=" + thread.interrupted());
			System.out.println("是否停止2？=" + thread.interrupted());
		} catch (InterruptedException e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end!");
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			super.run();
			for (int i = 0; i < 50000; i++) {
				System.out.println("i=" + (i + 1));
			}
		}
	}

}
