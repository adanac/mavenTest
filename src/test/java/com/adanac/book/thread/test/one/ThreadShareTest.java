package com.adanac.book.thread.test.one;

import org.junit.Test;

public class ThreadShareTest {

	@Test
	public void test_notshare() {
		NotShare a = new NotShare("A");
		NotShare b = new NotShare("B");
		NotShare c = new NotShare("C");
		a.start();
		b.start();
		c.start();
	}

	@Test
	public void test_share() {
		ThreadShare mythread = new ThreadShare();

		Thread a = new Thread(mythread, "A");
		Thread b = new Thread(mythread, "B");
		Thread c = new Thread(mythread, "C");
		Thread d = new Thread(mythread, "D");
		Thread e = new Thread(mythread, "E");
		a.start();
		b.start();
		c.start();
		d.start();
		e.start();
	}

	class NotShare extends Thread {

		private int count = 5;

		public NotShare(String name) {
			super();
			this.setName(name);
		}

		@Override
		public void run() {
			super.run();
			while (count > 0) {
				count--;
				System.out.println("由 " + this.currentThread().getName() + " 计算，count=" + count);
			}
		}
	}

	class ThreadShare extends Thread {

		private int count = 5;

		@Override
		synchronized public void run() {
			super.run();
			count--;
			System.out.println("由 " + this.currentThread().getName() + " 计算，count=" + count);
		}
	}

}
