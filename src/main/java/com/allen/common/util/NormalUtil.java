package com.allen.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

public class NormalUtil {

	public static Date GetDateByLong(long ts) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = sdf.parse(sdf.format(ts));

		return date;

	}

	public static Long GetLongByDate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date dt = format.parse(date);

		return dt.getTime() / 1000;

	}

	public static boolean isNullOrEmpty(String input) {
		return input == null || input.length() == 0;
	}

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// 单位：元转换成分
	public static Long GetLongByFloat(double num) throws ParseException {
		if (num == 0) {
			return 0L;
		}

		float str = (float) (num * 100);

		return (long) str;

	}

	// 单位：元转换成分
	public static Long GetLongByDouble(double num) throws ParseException {
		if (num == 0) {
			return 0L;
		}

		double str = num * 100;

		return (long) str;

	}

	/**
	 * 格式化元转分
	 * 
	 * @param num
	 * @return
	 */
	public static Long formatYuanByFen(String num) {
		BigDecimal bignum1 = new BigDecimal(num);
		BigDecimal bignum2 = new BigDecimal("100");
		BigDecimal bignum3 = bignum1.multiply(bignum2);

		System.out.println("res:" + bignum3.longValue());
		return bignum3.longValue();
	}

	public static String decodeToString(String str) {
		try {
			return URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatString(Object obj) {
		if (obj == null || "".equals(obj) || "null".equals(obj)) {
			return "";
		}
		return String.valueOf(obj);
	}

	/**
	 * list<Map>以map某一个Key值排序
	 * 
	 * @param data
	 *            list
	 * @param params
	 *            key
	 * @param type
	 *            0:升序1:降序
	 */
	public static void sort(List<Map<String, Object>> data, final String params, final int type) {

		Collections.sort(data, new Comparator<Map>() {

			public int compare(Map o1, Map o2) {

				String a = (String) o1.get(params);
				String b = (String) o2.get(params);

				if (type == 0) {
					return a.compareTo(b);
				} else {
					return b.compareTo(a);
				}
				// 升序
				// return a.compareTo(b);

				// 降序
				// return b.compareTo(a);
			}
		});
	}

	/**
	 * 由list转为string
	 * 
	 * @param sourceList
	 * @return
	 */
	public static String getStringFromList(JSONArray sourceList) {
		String res = "";
		if (sourceList != null) {
			if (sourceList.size() == 1) {
				res = sourceList.getString(0);
			} else {
				for (int i = 0; i < sourceList.size(); i++) {
					res += sourceList.getString(i) + ",";
				}
				res = res.substring(0, res.length() - 1);
			}
		}
		return res;
	}

	public static long getFormatPrice(String price) {
		try {
			return NormalUtil.GetLongByFloat(Double.parseDouble(price));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1L;
	}

	public static void main(String[] args) {
		System.out.println(getFormatPrice("80.88"));
	}

	@Test
	public void testStr2Long() {
		String str = "87.1";
		System.out.println(new BigDecimal(str).longValue());
	}
}
