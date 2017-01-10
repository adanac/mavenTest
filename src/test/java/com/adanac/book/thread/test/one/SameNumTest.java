package com.adanac.book.thread.test.one;

import org.junit.Test;

public class SameNumTest {

	/**
	 * initializationError(org.junit.runner.manipulation.Filter)
	 * java.lang.Exception: No tests found matching
	 * 解决：将 test修改为：test_samenum即可
	 * 
	 * 结果一：
	 *  i=5 threadName=Thread-2
		i=4 threadName=Thread-3
		i=3 threadName=Thread-4
		i=2 threadName=Thread-1
		i=1 threadName=Thread-5
		
		结果二：
		i=5 threadName=Thread-1
	
		i=4 threadName=Thread-2
		
		i=3 threadName=Thread-3
		
		i=2 threadName=Thread-4
		
		i=1 threadName=Thread-5
	 */
	@Test
	public void test_samenum() {
		MyThread run = new MyThread();

		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		Thread t3 = new Thread(run);
		Thread t4 = new Thread(run);
		Thread t5 = new Thread(run);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

	class MyThread extends Thread {

		private int i = 5;

		@Override
		public void run() {
			println("i=" + (i--) + " threadName=" + Thread.currentThread().getName());
		}

		public void println(String x) {
			synchronized (this) {
				print(x);
				newLine();
			}
		}

		private void newLine() {
			System.out.println();

		}

		private void print(String x) {
			System.out.println(x);

		}

	}
}
