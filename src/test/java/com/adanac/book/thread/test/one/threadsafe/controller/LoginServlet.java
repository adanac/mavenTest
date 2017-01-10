package com.adanac.book.thread.test.one.threadsafe.controller;

//本类模拟成一个Servlet组件
public class LoginServlet {

	private static String usernameRef;
	private static String passwordRef;

	synchronized public static void doPost(String username, String password) {
		try {
			usernameRef = username;
			passwordRef = password;

			System.out.println("username=" + usernameRef + " password=" + password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
