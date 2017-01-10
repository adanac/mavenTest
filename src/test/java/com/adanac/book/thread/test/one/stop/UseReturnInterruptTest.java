package com.adanac.book.thread.test.one.stop;

import org.junit.Test;

public class UseReturnInterruptTest {

	/**
	 * 将方法interrupt() 与 return结合使用也能实现停止线程的效果
	 * 但还是推荐使用"抛异常"的方法来实现线程的停止 
	 */
	@Test
	public void test() {
		MyThread t = new MyThread();
		t.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.interrupt();
	}

	class MyThread extends Thread {

		@Override
		public void run() {
			while (true) {
				if (this.isInterrupted()) {
					System.out.println("停止了!");
					return;
				}
				System.out.println("timer=" + System.currentTimeMillis());
			}
		}

	}
}
