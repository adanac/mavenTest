package com.allen.common.test;

import org.junit.Test;

public class OperateTest {
	@Test
	public void testPlus() {
		int i = 0;
		i = i++;
		System.out.println(i);// 0
	}

	@Test
	public void testPlus2() {
		int i = 0;
		i++;
		System.out.println(i);// 1
	}

}
