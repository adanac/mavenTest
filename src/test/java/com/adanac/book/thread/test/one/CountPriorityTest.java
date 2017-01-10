package com.adanac.book.thread.test.one;

import org.junit.Test;

public class CountPriorityTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		try {
			ThreadA a = new ThreadA();
			a.setPriority(Thread.NORM_PRIORITY - 3);
			a.start();

			ThreadB b = new ThreadB();
			b.setPriority(Thread.NORM_PRIORITY + 3);
			b.start();

			Thread.sleep(5000);
			a.stop();
			b.stop();

			System.out.println("a=" + a.getCount());
			System.out.println("b=" + b.getCount());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class ThreadA extends Thread {

		private int count = 0;

		public int getCount() {
			return count;
		}

		@Override
		public void run() {
			while (true) {
				count++;
			}
		}

	}

	class ThreadB extends Thread {

		private int count = 0;

		public int getCount() {
			return count;
		}

		@Override
		public void run() {
			while (true) {
				count++;
			}
		}

	}
}
