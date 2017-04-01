package com.allen.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.allen.common.constant.StringConstants;

/**
 * Copyright: 2016 Haiziwang
 * *
 * Author:  Daniel Kong
 * Date:    2016-02-27
 * Desc:    字符串工具
 */
public class StringUtils {

	private static final String REG_EX_DIGITAL = "[^0-9]";

	/**
	 * 获取非空内容
	 *
	 * @param str 输入字符串
	 * @return 非空字符串
	 */
	public static String getValidString(String str) {
		return (str == null) ? "" : str;
	}

	/**
	 * 字符串是否为空
	 *
	 * @param str 输入字符串
	 * @return 若为空返回 true
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 字符串是否非空
	 *
	 * @param str 输入字符串
	 * @return 若非空返回 true
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 将 string list 内容拼接打印
	 *
	 * @param strList         字符串数组
	 * @param concatCharacter 连接字符
	 * @return 拼接后的字符串
	 */
	public static String concatStrFromList(List<String> strList, String concatCharacter) {
		if (concatCharacter == null)
			concatCharacter = StringConstants.DEFAULT_STRING_SEPARATOR;

		if (strList != null && !strList.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			int size = strList.size();

			for (int i = 0; i < size; ++i) {
				sb.append(strList.get(i));
				if (i < size - 1)
					sb.append(concatCharacter);
			}
			return sb.toString();
		}
		return StringConstants.EMPTY_STRING;
	}

	/**
	 * 提取字符串中的数字序列
	 *
	 * @param inputStr 待处理的字符串
	 * @return 提取出的数字序列
	 */
	public static String extractDigitalNumbers(String inputStr) {
		Pattern p = Pattern.compile(REG_EX_DIGITAL);
		Matcher m = p.matcher(inputStr);
		return m.replaceAll(StringConstants.EMPTY_STRING).trim();
	}

	/**
	 * 格式化日期类型字符串
	 * @param date
	 * @return
	 */
	public static String formatDate(String date) {
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
	}

	/**
	 * 将异常Exception 或 错误Error 的堆栈信息转为字符串
	 *
	 * @param e 异常或错误
	 * @return 堆栈
	 */
	public static String getErrorInfoFromException(Throwable e) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return sw.toString();
		} catch (Throwable e2) {
			return StringConstants.EMPTY_STRING;
		}
	}

	public static String generateSpecStr(int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < count; i++) {
			sb.append(i).append(",");
		}
		return sb.substring(0, sb.length() - 1);
	}

	public static void main(String[] args) {
		// System.out.println(extractDigitalNumbers("CKYC201611140000003"));
		// System.out.println(constructAoOrderNo("CKYC201611140000003",
		// InventoryBackTaskTypeEnum.INVBACK_PROCESSING));
		System.out.println(getErrorInfoFromException(new RuntimeException("test123")));
		System.out.println(extractDigitalNumbers("lafz8981234"));
		System.out.println(formatDate("20171112"));
		System.out.println(generateSpecStr(2000));
	}
}
