package com.adanac.book.concurrency.test.one;

public class Synchronized {
	public static void main(String[] args) {
		// 对Synchronized Class对象进行加锁
		synchronized (Synchronized.class) {
		}
		m();
	}

	public static synchronized void m() {
	}

}
