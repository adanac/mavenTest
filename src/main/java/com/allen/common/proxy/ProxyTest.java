package com.allen.common.proxy;

import java.lang.reflect.InvocationHandler;

import org.junit.Test;

import com.allen.common.proxy.v1.ProxyVersion_0;
import com.allen.common.proxy.v2.ProxyVersion_1;
import com.allen.common.proxy.v3.HelloInvocationHandler;
import com.allen.common.proxy.v3.ProxyVersion_2;

public class ProxyTest {

	@Test
	public void test_proxy_v0() throws Exception {
		long start = System.currentTimeMillis();
		HelloWorld helloWorld = (HelloWorld) ProxyVersion_0.newProxyInstance();
		System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
		helloWorld.print();
		System.out.println();
	}

	@Test
	public void test_proxy_v1() throws Exception {
		long start = System.currentTimeMillis();
		HelloWorld helloWorld = (HelloWorld) ProxyVersion_1.newProxyInstance(HelloWorld.class);
		System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
		helloWorld.print();
		System.out.println();
	}

	@Test
	public void test_proxy_v2() throws Exception {
		long start = System.currentTimeMillis();
		HelloWorld helloWorldImpl = new HelloWorldImpl();
		InvocationHandler ih = new HelloInvocationHandler(helloWorldImpl);
		HelloWorld helloWorld = (HelloWorld) ProxyVersion_2.newProxyInstance(HelloWorld.class, ih);
		System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
		helloWorld.print();
		System.out.println();
	}
}
