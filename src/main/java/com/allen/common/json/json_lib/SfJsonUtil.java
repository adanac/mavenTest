package com.allen.common.json.json_lib;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SfJsonUtil {

	/**
	 * 页面向后台action传递一个json字符串，需要将json字符串转换成Map对象
	 * @param object
	 * @return
	 */
	public static Map<String, String> jsontoMap(Object object) {
		Map<String, String> data = new HashMap<String, String>();
		// 将json字符串转换成jsonObject
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(object);
		Iterator ite = jsonObject.keys();
		// 遍历jsonObject数据,添加到Map对象
		while (ite.hasNext()) {
			String key = ite.next().toString();
			String value = jsonObject.get(key).toString();
			data.put(key, value);
		}
		// 或者直接将 jsonObject赋值给Map
		// data = jsonObject;
		return data;
	}
}
