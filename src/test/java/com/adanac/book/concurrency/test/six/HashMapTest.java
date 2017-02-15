package com.adanac.book.concurrency.test.six;

import java.util.HashMap;
import java.util.UUID;

import org.junit.Test;

public class HashMapTest {
	@Test
	public void test1() throws InterruptedException {
		final HashMap<String, String> map = new HashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
					}, "ftf" + i).start();
				}
				System.out.println(map.size());
			}
		}, "ftf");
		t.start();
		t.join();
	}

}
