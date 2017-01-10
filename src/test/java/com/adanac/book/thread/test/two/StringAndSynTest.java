package com.adanac.book.thread.test.two;

import org.junit.Test;

public class StringAndSynTest {

	@Test
	public void test() {
		Service service = new Service();

		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();

		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();
	}

	class Service {
		public void print(String stringParam) {
			try {
				synchronized (stringParam) {
					while (true) {
						System.out.println(Thread.currentThread().getName());
						Thread.sleep(1000);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class ThreadA extends Thread {
		private Service service;

		public ThreadA(Service service) {
			super();
			this.service = service;
		}

		@Override
		public void run() {
			service.print("AA");
		}
	}

	class ThreadB extends Thread {
		private Service service;

		public ThreadB(Service service) {
			super();
			this.service = service;
		}

		@Override
		public void run() {
			service.print("AA");
		}
	}

}
