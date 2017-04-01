package com.allen.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.dubbo.common.utils.StringUtils;

public class StringTest {

	@Test
	public void test1() {
		String ids = "101";
		for (int i = 0; i < 3; i++) {
			ids = ids + ",";
		}
		System.out.println(ids);
	}

	@Test
	public void test2() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String activityIds = "1000002,1000003";
		if (StringUtils.isEmpty(activityIds)) {
			paramMap.put("activityIds", "");
		} else {
			if (activityIds.contains(",")) {
				String[] tempStrArray = StringUtils.split(activityIds, ',');

				for (int i = 0; i < tempStrArray.length; i++) {
					tempStrArray[i] = "'" + tempStrArray[i] + "'";
				}
				String activityids = StringUtils.join(tempStrArray, ',');
				paramMap.put("activityIds", activityids);
			} else {
				paramMap.put("activityIds", "'" + activityIds + "'");
			}
		}
		System.out.println(paramMap);// {activityIds='1000002','1000003'}
	}

	@Test
	public void test3() {
		ArrayList<String> skuIds = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			skuIds.add("10000" + i);
		}
		String sIds = StringUtils.join(skuIds, ",");
		System.out.println(sIds);// 100000,100001,100002
	}
}
