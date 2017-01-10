package com.adanac.book.thread.test.one.threadsafe;

import org.junit.Test;

import com.adanac.book.thread.test.one.threadsafe.extthread.ALogin;
import com.adanac.book.thread.test.one.threadsafe.extthread.BLogin;

public class ThreadSafeTest {

	/**
	 * username=a password=aa
	 * username=b password=bb
	 */

	@Test
	public void test_safe() {
		ALogin a = new ALogin();
		a.start();
		BLogin b = new BLogin();
		b.start();
	}
}
