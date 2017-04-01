package com.allen.common.collection.list;

import org.junit.Test;

import java.util.Vector;

public class VectorTest {

	@Test
	public void test_vector() {
		Vector<String> vector = new Vector<String>();
		String str = "a1,a2,a3,a1";

		String[] res_arr = new String[str.split(",").length];// 必须声明长度，否则
		res_arr = str.split(",");
		System.out.println("res_arr.length:" + res_arr.length);
		for (int i = 0; i < res_arr.length; i++) {
			vector.add(res_arr[i]);
		}
		System.out.println(vector.size());
	}
}
