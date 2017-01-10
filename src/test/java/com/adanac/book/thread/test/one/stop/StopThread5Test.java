package com.adanac.book.thread.test.one.stop;

import org.junit.Test;

public class StopThread5Test {

	/**
	 * 条件：先sleep再interrupt
	 * 结论：如果再sleep状态下停止某一线程，会进入catch语句，并且清除停止状态值，使之变为false
	 */
	@Test
	public void test() {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(200);
			thread.interrupt();
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
			try {
				System.out.println("run begin");
				Thread.sleep(200000);
				System.out.println("run end");
			} catch (InterruptedException e) {
				System.out.println("在沉睡中被停止!进入catch!" + this.isInterrupted());
				e.printStackTrace();
			}
		}
	}

}
