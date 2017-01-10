package com.adanac.book.thread.test.one.stop;

import org.junit.Test;

public class UserStopMethodThreadTest {

	@Test
	public void test() {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(8000);
			thread.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class MyThread extends Thread {
		private int i = 0;

		@Override
		public void run() {
			try {
				while (true) {
					i++;
					System.out.println("i=" + i);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
