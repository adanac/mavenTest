package com.allen.common.json.json_lib;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ParseJsonString {

	@Test
	public void parse_1() {
		String jsonString = "{\"UserName\":\"ZHULI\",\"age\":\"30\",\"workIn\":\"ALI\",\"Array\":[\"ZHULI\",\"30\",\"ALI\"]}";
		// 将Json字符串转为java对象
		JSONObject obj = JSONObject.fromObject(jsonString);
		// 获取Object中的UserName
		if (obj.has("UserName")) {
			System.out.println("UserName:" + obj.getString("UserName"));
		}
		// 获取ArrayObject
		if (obj.has("Array")) {
			JSONArray transitListArray = obj.getJSONArray("Array");
			for (int i = 0; i < transitListArray.size(); i++) {
				System.out.print("Array:" + transitListArray.getString(i) + " ");
			}
		}
	}
}
