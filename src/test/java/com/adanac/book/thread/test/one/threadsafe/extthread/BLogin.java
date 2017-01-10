package com.adanac.book.thread.test.one.threadsafe.extthread;

import com.adanac.book.thread.test.one.threadsafe.controller.LoginServlet;

public class BLogin extends Thread {
	@Override
	public void run() {
		LoginServlet.doPost("b", "bb");
	}
}
