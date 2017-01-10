package com.adanac.book.thread.test.one;

import org.junit.Test;

public class ResumeLockStopTest {

	/**
	 * MyThread.run() 添加System.out.println(i);语句后，控制台不会打印 main end!
	 * 原因：当程序运行到println方法内部停止时，同步锁未被释放，导致当前PrintStream对象的println()方法一直呈暂停状态，
	 * 并且锁未释放，而main方法中的代码System.out.println("main end!");迟迟不能打印
	 */
	@Test
	public void test() {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(1000);
			thread.suspend();
			System.out.println("main end!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class MyThread extends Thread {
		private long i = 0;

		@Override
		public void run() {
			while (true) {
				i++;
				// System.out.println(i);
			}
		}
	}

}
