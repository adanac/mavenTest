package com.adanac.book.thread.test.two;

import org.junit.Test;

public class SynchronizedTest {

	@Test
	public void test() throws InterruptedException {
		Task task = new Task();

		MyThread1 thread1 = new MyThread1(task);
		thread1.start();

		Thread.sleep(100);

		MyThread2 thread2 = new MyThread2(task);
		thread2.start();
	}

	class Task {
		synchronized public void otherMethod() {
			System.out.println("------------------------run--otherMethod");
		}

		public void doLongTimeTask() {
			synchronized (this) {
				for (int i = 0; i < 10000; i++) {
					System.out.println("synchronized threadName=" + Thread.currentThread().getName() + " i=" + (i + 1));
				}
			}

		}
	}

	class MyThread1 extends Thread {

		private Task task;

		public MyThread1(Task task) {
			super();
			this.task = task;
		}

		@Override
		public void run() {
			super.run();
			task.doLongTimeTask();
		}

	}

	class MyThread2 extends Thread {

		private Task task;

		public MyThread2(Task task) {
			super();
			this.task = task;
		}

		@Override
		public void run() {
			super.run();
			task.otherMethod();
		}

	}

}
