package com.adanac.book.thread.test.one;

import org.junit.Test;

public class ResumeThreadTest {

	/**
	 * 线程的确被暂停了，而且还可以恢复成运行状态。
	 * 使用suspend 与 resume 方法的缺点：
	 * 如果使用不当，极易造成公共的同步对象的独占，使得其他线程无法访问公共同步对象
	 */
	@Test
	public void test() {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(5000);
			// A
			thread.suspend();
			System.out.println("A= " + System.currentTimeMillis() + " i=" + thread.getI());
			Thread.sleep(5000);
			System.out.println("A= " + System.currentTimeMillis() + " i=" + thread.getI());
			// B
			thread.resume();
			Thread.sleep(5000);

			// C
			thread.suspend();
			System.out.println("B= " + System.currentTimeMillis() + " i=" + thread.getI());
			Thread.sleep(5000);
			System.out.println("B= " + System.currentTimeMillis() + " i=" + thread.getI());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class MyThread extends Thread {

		private long i = 0;

		public long getI() {
			return i;
		}

		public void setI(long i) {
			this.i = i;
		}

		@Override
		public void run() {
			while (true) {
				i++;
			}
		}

	}

}
