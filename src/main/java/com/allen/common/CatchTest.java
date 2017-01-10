package com.allen.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.allen.common.util.MTConstants;
import com.allen.common.util.NormalUtil;

public class CatchTest {
	List<Object> checkParam(String param) {
		List<Object> ret = new ArrayList<Object>();
		List<String> codes = new ArrayList<String>();
		String[] array = null;
		boolean flag = true;
		if (NormalUtil.isNullOrEmpty(param)) {
			ret.add(false);
			ret.add(codes);
			return ret;
		}
		if (param.indexOf(MTConstants.FEN_CN_SEPARATOR) != -1) {
			array = param.split(MTConstants.FEN_CN_SEPARATOR);
		} else {
			array = new String[] { param };
		}
		int count = 0;
		for (String g : array) {
			try {
				Long.parseLong(g);
				codes.add(g);
				count++;
			} catch (Exception e) {
				flag = false;
			}
		}
		ret.add(flag);
		ret.add(codes);
		ret.add(count);
		return ret;
	}

	/**
	 * false,2
	 */
	@Test
	public void test_try() {
		String str = "b；1234；c；222；a";
		List<Object> res = checkParam(str);
		System.out.println(res.get(0) + "," + res.get(2));
	}

	@Test
	public void test_catch() {
		try {
			int i = 1 / 0;
			System.out.println(i);
		} catch (Exception e) {
			System.out.println("Exception has happend.");
		}
	}

	@Test
	public void test_for_exception() {
		boolean flag = true;
		try {
			BigDecimal sp = new BigDecimal(1);// 定价金额
			BigDecimal sr = new BigDecimal(1000);
			BigDecimal count = new BigDecimal(3);
			BigDecimal spr = sp.multiply(sr).divide(count);
			BigDecimal temp = spr.divide(new BigDecimal(10000));
			String check = temp.toString();
			if (check.indexOf(".") >= 0) {
				int dot = check.indexOf(".");
				String dotNum = check.substring(dot + 1, check.length());
				if (dotNum.length() > 2) {
					flag = false;
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		if (!flag) {
			System.out.println("exception has happend");
		}
	}
}
