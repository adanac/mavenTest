package com.allen.common.json.json_lib;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.allen.common.json.json_lib.bean.CompanyBean;
import com.allen.common.json.json_lib.bean.JobBean;
import com.allen.common.json.json_lib.bean.UserBean;

import net.sf.json.JSONObject;

public class JsonToBean {
	/*
	 * 在JSONObject.toBean的时候如果转换的类中有集合,可以先定义Map<String, Class> classMap = new
	 * HashMap<String, Class>();在classMap中put你要转换的类中的集合名,像:classMap.put(
	 * "teachers", Teacher.class);然后在toBean()的时候把参数加上, 像:Student
	 * student=(Student) JSONObject.toBean(str, Student.class, classMap);
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Test
	public void test_jsonToBean() {
		String jsonString = "";
		// 转换成Json对象
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("userList", UserBean.class);
		classMap.put("jobList", JobBean.class);
		// 将json转换成workflowBean
		CompanyBean companyBean = (CompanyBean) JSONObject.toBean(jsonObj, CompanyBean.class, classMap);
	}
}
