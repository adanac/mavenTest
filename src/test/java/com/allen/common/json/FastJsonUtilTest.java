package com.allen.common.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allen.common.json.fastjson.FastJsonUtil;
import org.junit.Test;

import java.util.Map;

public class FastJsonUtilTest {

	@Test
	public void test_parseJsonStr() {
		String str = "{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"";
		FastJsonUtil.parseJsonStr(str);
	}

	@Test
	public void test_jsonObjectToMap(){
		JSONObject object = new JSONObject();
		object.put("name","allen");
		object.put("age",22);
		Map<String,Object> map = FastJsonUtil.jsonObjectToMap(object);
		System.out.println(JSON.toJSONString(map));
	}
}
