package com.adanac.book.thread.test.two;

import org.junit.Test;

public class SynBlockStringTest {
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

	class ThreadA extends Thread {
		private Service service;

		public ThreadA(Service service) {
			super();
			this.service = service;
		}

		@Override
		public void run() {
			service.setUsernamePassword("a", "aa");

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
			service.setUsernamePassword("b", "bb");

		}

	}

	class Service {

		private String usernameParam;
		private String passwordParam;

		public void setUsernamePassword(String username, String password) {
			try {
				String anyString = new String();
				synchronized (anyString) {
					System.out.println(
							"线程名称为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入同步块");
					usernameParam = username;
					// Thread.sleep(1);
					passwordParam = password;
					System.out.println(
							"线程名称为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开同步块");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
