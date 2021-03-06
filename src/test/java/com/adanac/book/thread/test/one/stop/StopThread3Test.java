package com.adanac.book.thread.test.one.stop;

import org.junit.Test;

public class StopThread3Test {

	@Test
	public void test() {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(2000);
			thread.interrupt();
		} catch (InterruptedException e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end!");
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			super.run();
			for (int i = 0; i < 500000; i++) {
				if (this.interrupted()) {
					System.out.println("已经是停止状态了!我要退出了!");
					break;
				}
				System.out.println("i=" + (i + 1));
			}
			System.out.println("我被输出，如果此代码是for又继续运行，线程并未停止！");
		}
	}

}
