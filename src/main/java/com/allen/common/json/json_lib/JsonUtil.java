package com.allen.common.json.json_lib;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import net.sf.json.JSONArray;

public class JsonUtil {
	/**
	 * 从json数组中得到相应java数组 JSONArray下的toArray()方法的使用
	 * 从json数组中得到相应java数组，如果要获取java数组中的元素，只需要遍历该数组。
	 * 
	 * @param str
	 * @return
	 */
	public static Object[] getJsonToArray(String str) {
		JSONArray jsonArray = JSONArray.fromObject(str);
		return jsonArray.toArray();
	}

	@Test
	public void test_getJsonToArray() {
		JSONArray jsonStrs = new JSONArray();
		jsonStrs.add(0, "cat");
		jsonStrs.add(1, "dog");
		jsonStrs.add(2, "bird");
		jsonStrs.add(3, "duck");

		Object[] obj = getJsonToArray(jsonStrs.toString());
		for (int i = 0; i < obj.length; i++) {
			System.out.println(obj[i]);
		}
	}

	/**
	 * 将json数组转化为Long型
	 * 
	 * @param str
	 * @return
	 */
	public static Long[] getJsonToLongArray(String str) {
		JSONArray jsonArray = JSONArray.fromObject(str);
		Long[] arr = new Long[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			arr[i] = jsonArray.getLong(i);
			System.out.println(arr[i]);
		}
		return arr;
	}

	@Test
	public void test_getJsonToLongArray() {
		JSONArray jsonLongs = new JSONArray();
		jsonLongs.add(0, "111");
		jsonLongs.add(1, "222.25");
		jsonLongs.add(2, new Long(333));
		jsonLongs.add(3, 444);

		Long[] log = getJsonToLongArray(jsonLongs.toString());
		for (int i = 0; i < log.length; i++) {
			System.out.println(log[i]);
		}
	}

	/**
	 * 将json数组转化为String型
	 * 
	 * @param str
	 * @return
	 */
	public static String[] getJsonToStringArray(String str) {
		JSONArray jsonArray = JSONArray.fromObject(str);
		String[] arr = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			arr[i] = jsonArray.getString(i);
			System.out.println(arr[i]);
		}
		return arr;
	}

	/**
	 * 将json数组转化为Double型
	 * 
	 * @param str
	 * @return
	 */
	public static Double[] getJsonToDoubleArray(String str) {
		JSONArray jsonArray = JSONArray.fromObject(str);
		Double[] arr = new Double[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			arr[i] = jsonArray.getDouble(i);
		}
		return arr;
	}

	/**
	 * 将json数组转化为Date型
	 * 
	 * @param str
	 * @return
	 */
	public static Date[] getJsonToDateArray(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Date[] dateArray = new Date[jsonArray.size()];
		String dateString;
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < jsonArray.size(); i++) {
			dateString = jsonArray.getString(i);
			try {
				date = sdf.parse(dateString);
				dateArray[i] = date;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dateArray;
	}

	@Test
	public void test_getJsonToDateArray() {

		JSONArray jsonStrs = new JSONArray();
		jsonStrs.add(0, "2011-01-01");
		jsonStrs.add(1, "2011-01-03");
		jsonStrs.add(2, "2011-01-04 11:11:11");

		Date[] d = getJsonToDateArray(jsonStrs.toString());
		for (int i = 0; i < d.length; i++) {
			System.out.println(d[i]);
		}
	}

}