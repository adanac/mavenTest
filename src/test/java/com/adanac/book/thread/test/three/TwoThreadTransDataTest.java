package com.adanac.book.thread.test.three;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TwoThreadTransDataTest {

	@Test
	public void test() {
		MyList service = new MyList();

		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();

		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();
	}

	class MyList {

		private List list = new ArrayList();

		public void add() {
			list.add("高洪岩");
		}

		public int size() {
			return list.size();
		}

	}

	class ThreadA extends Thread {

		private MyList list;

		public ThreadA(MyList list) {
			super();
			this.list = list;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < 10; i++) {
					list.add();
					System.out.println("添加了" + (i + 1) + "个元素");
					// Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	class ThreadB extends Thread {

		private MyList list;

		public ThreadB(MyList list) {
			super();
			this.list = list;
		}

		@Override
		public void run() {
			try {
				while (true) {
					if (list.size() == 5) {
						System.out.println("==5了，线程b要退出了！");
						throw new InterruptedException();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
