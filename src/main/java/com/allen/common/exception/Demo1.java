package com.allen.common.exception;

public class Demo1 {

	public static void main(String[] args) {
		System.out.println(test1());
	}

	public static int test1() {
		int ret = 0;
		try {
			throw new Exception();
		} catch (Exception e) {
			ret = 1;
			return ret;
		} finally {
			ret = 2;
		}
	}
}
