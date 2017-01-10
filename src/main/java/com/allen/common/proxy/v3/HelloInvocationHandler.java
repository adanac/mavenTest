package com.allen.common.proxy.v3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloInvocationHandler implements InvocationHandler {
	private Object obj;

	public HelloInvocationHandler(Object obj) {
		this.obj = obj;
	}

	public void invoke(Object proxy, Method method) {
		System.out.println("Before Hello World!");
		try {
			method.invoke(obj, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After Hello World!");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Before Hello World!");
		try {
			method.invoke(obj, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After Hello World!");
		return null;
	}
}