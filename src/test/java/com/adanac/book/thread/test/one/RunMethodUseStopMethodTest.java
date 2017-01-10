package com.adanac.book.thread.test.one;

import org.junit.Test;

public class RunMethodUseStopMethodTest {

	/**
	 * stop方法已经被作废
	 * 因为如果强制让线程停止，则：
	 * 1.有可能使一些清理性的工作得不到完成。
	 * 2.对锁定的对象进行了解锁，导致数据得不到同步的处理，出现数据不一致情况。
	 */
	@Test
	public void test() {
		MyThread thread = new MyThread();
		thread.start();
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			try {
				this.stop();
			} catch (ThreadDeath e) {
				System.out.println("进入了catch()方法！");
				e.printStackTrace();
			}
		}
	}

}
