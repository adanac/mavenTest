package com.allen.common.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class MapTest {

	/**map根据key取值，key类型也要与原来存储数据一样才能取到**/
	@Test
	public void test_notGetvalue() {

		Map<String, String> skuMap = new HashMap<String, String>();
		skuMap.put("111", "12");
		skuMap.put("222", "23");
		skuMap.put("333", "34");

		System.err.println(skuMap.get(Long.parseLong("111")));// null
		Assert.assertNull(skuMap.get(Long.parseLong("111")));
		System.out.println(skuMap.get("111"));// 12
		Assert.assertEquals("12", skuMap.get("111"));
	}

	@Test
	public void testValues() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < 10; i++) {
			map.put("" + i + 1, "Name" + i);
		}
		System.out.println(map.values());
		System.out.println(JSON.toJSONString(map.values()));
		System.out.println(JSON.toJSONString(map));
	}

	@Test
	public void test1() throws Exception {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < 10; i++) {
			map.put("" + i + 1, "Name" + i);
		}
		map.put("01", "F_");
		map.put("11", "Name1");
		map.put("21", "F_Nme");

		System.out.println(map.size());
	}

	@Test
	public void test_hashMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", 1);
		map.put("type", 2);
		System.out.println(map.toString());// {type=2}
	}

	@Test
	public void test_LinkedHashMap() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("type", 1);
		map.put("type", 2);
		System.out.println(map.toString());// {type=2}
	}

	@Test
	public void test_TreeMap() {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("type", 1);
		map.put("type", 2);
		System.out.println(map.toString());// {type=2}
	}

}
