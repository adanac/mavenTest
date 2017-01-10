package com.adanac.book.thread.test.one;

import org.junit.Test;

public class ResumeNosameValueTest {

	/**
	 * 停止a线程！
	 * a 11
	 * suspend方法使用要注意，有可能出现值不同步的情况
	 * 
	 */
	@Test
	public void test() {
		final MyObject myobject = new MyObject();

		Thread thread1 = new Thread() {
			public void run() {
				myobject.setValue("a", "aa");
			};
		};
		thread1.setName("a");
		thread1.start();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread thread2 = new Thread() {
			public void run() {
				myobject.printUsernamePassword();
			};
		};
		thread2.start();
	}

	class MyObject {

		private String username = "1";
		private String password = "11";

		public void setValue(String u, String p) {
			this.username = u;
			if (Thread.currentThread().getName().equals("a")) {
				System.out.println("停止a线程！");
				Thread.currentThread().suspend();
			}
			this.password = p;
		}

		public void printUsernamePassword() {
			System.out.println(username + " " + password);
		}
	}

}
