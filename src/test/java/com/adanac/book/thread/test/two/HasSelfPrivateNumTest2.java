package com.adanac.book.thread.test.two;

import org.junit.Test;

public class HasSelfPrivateNumTest2 {
	/**
	 * 如果对象仅有一个实例变量，则有可能出现覆盖的情况
	 */
	@Test
	public void test() {
		HasSelfPrivateNum numRef = new HasSelfPrivateNum();

		ThreadA athread = new ThreadA(numRef);
		athread.run();

		ThreadB bthread = new ThreadB(numRef);
		bthread.run();
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

	class HasSelfPrivateNum {

		private int num = 0;

		synchronized public void addI(String username) {
			try {
				if (username.equals("a")) {
					num = 100;
					System.out.println("a set over!");
					Thread.sleep(2000);
				} else {
					num = 200;
					System.out.println("b set over!");
				}
				System.out.println(username + " num=" + num);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
