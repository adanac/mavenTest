package com.allen.common.proxy.v3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.allen.common.proxy.HelloWorld;

public class $Proxy1 implements HelloWorld {
	InvocationHandler h;

	public $Proxy1(InvocationHandler h) {
		this.h = h;
	}

	public void print() {
		try {
			Method md = com.allen.common.proxy.HelloWorld.class.getMethod("print");
			// h.invoke(this, md);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}