package com.adanac.book.thread.test.one.stop;

import org.junit.Test;

/**
 * Copyright: 2016 Haiziwang
 * *
 * Author:  fzLiu
 * Date:    2016年12月22日
 * Desc:    desc
 */
public class UseExceptionStopThreadTest {
	/**
	 * 能停止的线程,异常法（推荐）
	 * 在catch块中还可以将异常向上抛，使线程停止的事件得以传播
	 * this.interrupted() 判断线程是否是停止状态
	 */
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
			try {
				for (int i = 0; i < 500000; i++) {
					if (this.interrupted()) {
						System.out.println("已经是停止状态了!我要退出了!");
						throw new InterruptedException();
					}
					System.out.println("i=" + (i + 1));
				}
				System.out.println("我在for下面");
			} catch (InterruptedException e) {
				System.out.println("进MyThread.java类run方法中的catch了！");
				e.printStackTrace();
			}
		}
	}

}
