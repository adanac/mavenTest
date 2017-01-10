package com.adanac.book.concurrency;

public class ConcurrencyTest {

	protected static final long count = 10000l;
	protected static final int count_exe = 10;

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < count_exe; i++) {
			System.err.println("第 " + i + " 次执行....");
			concurrency();
			serial();
			System.out.println("----------------------------");
		}

	}

	private static void serial() throws InterruptedException {
		long start = System.currentTimeMillis();
		int a = 0;
		for (long i = 0; i < count; i++) {
			a += 5;
		}
		int b = 0;
		for (long i = 0; i < count; i++) {
			b--;
		}
		long time = System.currentTimeMillis() - start;
		System.err.println("serial:" + time + "ms," + b);

	}

	private static void concurrency() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable() {

			public void run() {
				int a = 0;
				for (long i = 0; i < count; i++) {
					a += 5;
				}
			}

		});
		thread.start();

		int b = 0;
		for (long i = 0; i < count; i++) {
			b--;
		}

		long time = System.currentTimeMillis() - start;
		thread.join();

		System.err.println("concurrency:" + time + "ms," + b);

	}

}
