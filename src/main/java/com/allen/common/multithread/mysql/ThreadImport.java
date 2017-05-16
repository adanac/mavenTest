package com.allen.common.multithread.mysql;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * CREATE TABLE `demo_table` (
	 `a` varchar(64) DEFAULT NULL,
	 `b` varchar(64) DEFAULT NULL
	 ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
public class ThreadImport {
	private String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8";
	private String user = "root";
	private String password = "root";

	public Connection getConnect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void multiThreadImport(final int ThreadNum) {
		final CountDownLatch cdl = new CountDownLatch(ThreadNum);
		long starttime = System.currentTimeMillis();
		for (int k = 1; k <= ThreadNum; k++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Connection con = getConnect();
					try {
						Statement st = con.createStatement();
						for (int i = 1; i <= 80000 / ThreadNum; i++) {
							String uuid = UUID.randomUUID().toString();
							st.addBatch("insert into demo_table(a,b) values('" + uuid + "','" + uuid + "')");
							if (i % 500 == 0) {
								st.executeBatch();
							}
						}
						cdl.countDown();
					} catch (Exception e) {
					} finally {
						try {
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		try {
			cdl.await();
			long spendtime = System.currentTimeMillis() - starttime;
			System.out.println(ThreadNum + "个线程花费时间:" + spendtime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test_thread1() {
		ThreadImport ti = new ThreadImport();
		//ti.multiThreadImport(1);// 1个线程花费时间:39600
//		ti.multiThreadImport(5);// 5个线程花费时间:12201
		ti.multiThreadImport(8);// 8个线程花费时间:8911
//		ti.multiThreadImport(10);// 10个线程花费时间:8673
//		ti.multiThreadImport(20);// 20个线程花费时间:6564
//		ti.multiThreadImport(40);// 40个线程花费时间:9411
		System.out.println("笔记本CPU数:" + Runtime.getRuntime().availableProcessors());
	}

	public static void main(String[] args) throws Exception {

	}

}