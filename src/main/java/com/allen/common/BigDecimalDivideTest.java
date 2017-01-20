package com.allen.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class BigDecimalDivideTest {
	@Test
	public void test_divide_exception_fix() {

		try {
			BigDecimal res1 = new BigDecimal(1).multiply(new BigDecimal(1)).divide(new BigDecimal(3), 3,
					RoundingMode.HALF_UP);
			BigDecimal res2 = new BigDecimal(2).multiply(new BigDecimal(3)).divide(new BigDecimal(5), 3,
					RoundingMode.HALF_UP);
			BigDecimal res3 = new BigDecimal(3).multiply(new BigDecimal(3)).divide(new BigDecimal(4), 3,
					RoundingMode.HALF_UP);
			System.out.println("res1:" + res1);
			System.out.println("res2:" + res2);
			System.out.println("res3:" + res3);

		} catch (Exception e) {
			System.out.println("Non-terminating decimal expansion; no exact representable decimal result.");
			e.printStackTrace();
		}

	}

	@Test
	public void test_divide_exception() {
		BigDecimal sp = new BigDecimal(1);// 定价金额
		BigDecimal sr = new BigDecimal(Long.toString(1));// 分摊比例
		BigDecimal count = new BigDecimal(Long.toString(3));// 商品数量

		try {
			BigDecimal spr = sp.multiply(sr).divide(count);
			BigDecimal temp = spr.divide(new BigDecimal(10000));
			String check = temp.toString();
			System.out.println(check);
		} catch (Exception e) {
			System.out.println("Non-terminating decimal expansion; no exact representable decimal result.");
			e.printStackTrace();
		}

	}

	/**
	 * 判断小数点的位数
	 */
	@Test
	public void test2() {
		// 是否为组合码商品，如果是组合码商品，对其作定价金额校验
		// 定价金额做强制校验：（定价金额*每行SKU分摊比例）/包含商品数不允许除不尽（小数点后两位）
		boolean flag = true;
		// 组合码商品
		BigDecimal bsetprice = new BigDecimal("58");// （定价金额）页面展示58
		BigDecimal bshareratio = new BigDecimal("4000");// （分摊比例）页面展示40%，但C++接口combInfoPo.getShareRatio()返回4000，所以最终结果要再处以10000.
		long combSkuNum = 2;// 商品数量
		BigDecimal finalResesult = bsetprice.multiply(bshareratio).divide(new BigDecimal(combSkuNum));// （combSkuNum：商品数量）
		BigDecimal temp = finalResesult.divide(new BigDecimal(10000));
		String check = temp.toString();
		if (check.indexOf(".") >= 0) {
			int dot = check.indexOf(".");
			String dotNum = check.substring(dot + 1, check.length());
			System.err.println("check:" + check + "\tdotNum:" + dotNum);
			if (dotNum.length() > 2) {
				flag = false;
			}
		}
		System.err.println("flag:" + flag);
		if (!flag) {
			System.err.println("验证失败。。。。。。。。。。");
		}

	}

	public static void main(String[] args) {

		// 是否为组合码商品，如果是组合码商品，对其作定价金额校验
		boolean flag = true;
		// 组合码商品

		long setprice = 29600;// 定价金额
		long shareRatio = 10000;// 分摊比例
		long combSkuNum = 2;// 商品数量
		BigDecimal sp = new BigDecimal(Long.toString(setprice));
		BigDecimal sr = new BigDecimal(Long.toString(shareRatio));
		BigDecimal count = new BigDecimal(Long.toString(combSkuNum));
		BigDecimal spr = sp.multiply(sr).multiply(count);
		BigDecimal temp = spr.divide(new BigDecimal(1000000));
		String check = temp.toString();
		// if (check.indexOf(".") >= 0) {
		int dot = check.indexOf(".");
		String dotNum = check.substring(dot + 1, check.length());
		System.err.println("check:" + check + "\tdotNum:" + dotNum);
		if (dotNum.length() > 2) {
			flag = false;
		}
		// }
		System.err.println("flag:" + flag);
		if (!flag) {
			System.err.println("验证失败。。。。。。。。。。");
		}

		test1();
	}

	public static void test1() {
		String tag = ".22222";
		System.out.println(tag.indexOf("."));// 0
		System.out.println(tag.indexOf(".") >= 0);// true
		System.out.println(tag.indexOf(".") > 0);// false
		System.out.println(tag.indexOf(".") == 0);// true
	}

}
