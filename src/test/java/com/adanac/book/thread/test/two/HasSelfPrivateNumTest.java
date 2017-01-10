package com.adanac.book.thread.test.two;

import org.junit.Test;

public class HasSelfPrivateNumTest {
	/**
	 * a set over!
		b set over!
		b num=200
	
	 */
	@Test
	public void test() {
		HasSelfPrivateNum numRef = new HasSelfPrivateNum();

		ThreadA athread = new ThreadA(numRef);
		athread.start();

		ThreadB bthread = new ThreadB(numRef);
		bthread.start();
	}

	class ThreadB extends Thread {

		private HasSelfPrivateNum numRef;

		public ThreadB(HasSelfPrivateNum numRef) {
			super();
			this.numRef = numRef;
		}

		@Override
		public void run() {
			super.run();
			numRef.addI("b");
		}

	}

	class ThreadA extends Thread {

		private HasSelfPrivateNum numRef;

		public ThreadA(HasSelfPrivateNum numRef) {
			super();
			this.numRef = numRef;
		}

		@Override
		public void run() {
			super.run();
			numRef.addI("a");
		}

	}

	class HasSelfPrivateNum {

		public void addI(String username) {
			try {
				int num = 0;
				if (username.equals("a")) {
					num = 100;
					System.out.println("a set over!");
					Thread.sleep(1000);
				} else {
					num = 200;
					System.out.println("b set over!");
				}
				System.out.println(username + " num=" + num);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
