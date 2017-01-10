package com.allen.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class MapTest {

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
