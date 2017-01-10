package com.adanac.book.thread.test.one;

import org.junit.Test;

public class YieldThreadTest {

	/**
	 * yield方法：
	 * 作用：放弃当前的CPU资源，将它让给其他的任务去占用CPU执行时间。但放弃的时间不确定
	 * 去除注释
	 * 结果：1333106752 用时：4963毫秒！
	 * 打开注释
	 * 结果：1333106752 用时：27毫秒！
	 */
	@Test
	public void test() {
		MyThread thread = new MyThread();
		thread.run();
	}

	class MyThread extends Thread {

		@Override
		public void run() {
			long beginTime = System.currentTimeMillis();
			int count = 0;
			for (int i = 0; i < 50000000; i++) {
				// Thread.yield();
				count = count + (i + 1);
			}
			long endTime = System.currentTimeMillis();
			System.out.println("结果：" + count + " 用时：" + (endTime - beginTime) + "毫秒！");
		}

	}

}
