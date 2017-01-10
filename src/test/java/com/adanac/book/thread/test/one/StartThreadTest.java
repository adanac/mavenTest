package com.adanac.book.thread.test.one;

import org.junit.Test;

public class StartThreadTest {

	/**
	 *   start（）:通过该方法启动线程的同时也创建了一个线程，真正实现了多线程，这是无需等待run（）方法中的代码执行完毕就可以直接执行下面的代码，通过start创建的线程处于可运行状态，当得到CPU时间片后就会执行其中的run方法，这里方法run（）称为线程体，它包含了要执行的这个线程的内容，Run方法运行结束，此线程随即终止。
	
	     run（）：通过run方法启动线程其实就是调用一个类中的方法，并没有创建一个线程，程序中还是只有主线程，还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下面的代码，这样就没有达到写线程的目的。
	 */
	@Test
	public void test1() {
		ThreadDemo01 thread = new ThreadDemo01();
		thread.start();
	}

	@Test
	public void test2() {
		Thread thread = new Thread(new ThreadDemo02());
		thread.start();
	}

	class ThreadDemo01 extends Thread {
		public void run() {
			System.out.println("线程启动01");
		}
	}

	class ThreadDemo02 implements Runnable {

		@Override
		public void run() {
			System.out.println("线程启动02");
		}

	}
}
