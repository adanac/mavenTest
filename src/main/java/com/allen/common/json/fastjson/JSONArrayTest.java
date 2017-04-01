package com.allen.common.json.fastjson;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JSONArrayTest {

	@Test
	public void test1() {

		List<Object> list = new ArrayList<Object>();
		list.add(8000);
		JSONArray sellerIdList = new JSONArray(list);
		if (sellerIdList != null && sellerIdList.size() > 0) {
			if (sellerIdList.contains("8000")) {
				System.out.println("包含");
			} else {
				System.out.println("不包含");
			}
		}
	}

	/**
	 * 包含
	 */
	@Test
	public void test() {

		String sellerId = "[\"3\",\"24\"]";
		long mySeller = 24l;
		JSONArray sellerIdList = JSONArray.parseArray(sellerId);
		if (sellerIdList != null && sellerIdList.size() > 0) {
			if (sellerIdList.contains(String.valueOf(mySeller))) {
				System.out.println("包含");
			} else {
				System.out.println("不包含");
			}
		}
	}
}
