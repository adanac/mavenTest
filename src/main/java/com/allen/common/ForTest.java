package com.allen.common;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ForTest {

	private static final int COUNT = 10;
	private static int for_count = 0;
	private static int same_count = 0;
	private static List<String> same_list = new LinkedList<String>();
	private static String[] str1 = new String[COUNT];
	private static String[] str2 = new String[COUNT * COUNT];

	@Before
	public void init() {
		for (int i = 0; i < COUNT; i++) {
			str1[i] = "aa" + "_" + i;
		}

		for (int i = 0; i < COUNT * COUNT; i++) {
			str2[i] = "aa" + "_" + i;
		}
	}

	/**
	 * 955，效率次之
	 */
	@Test
	public void test3() {
		for_count = 0;
		same_count = 0;
		same_list.clear();
		for (int j = 0; j < str2.length; j++) {
			for (int i = 0; i < str1.length; i++) {
				for_count++;
				if (str1[i].equals(str2[j])) {
					same_count++;
					same_list.add(str1[i]);
					break;
				}
			}
		}
		System.out.println(
				"for_count:" + for_count + ", same_count:" + same_count + ", same_list:" + same_list.toString());
	}

	/**
	 * 955，效率次之
	 */
	@Test
	public void test2() {
		for_count = 0;
		same_count = 0;
		same_list.clear();
		for (int j = 0; j < str2.length; j++) {
			for (int i = 0; i < str1.length; i++) {
				for_count++;
				if (str1[i].equals(str2[j])) {
					same_count++;
					same_list.add(str1[i]);
					break;
				}
			}
		}
		System.out.println(
				"for_count:" + for_count + ", same_count:" + same_count + ", same_list:" + same_list.toString());
	}

	/**
	 * 1000，效率最低
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		for_count = 0;
		same_count = 0;
		same_list.clear();

		for (int j = 0; j < str2.length; j++) {
			for (int i = 0; i < str1.length; i++) {
				for_count++;
				if (str1[i].equals(str2[j])) {
					same_count++;
					same_list.add(str1[i]);
				}
			}
		}
		System.out.println(
				"for_count:" + for_count + ", same_count:" + same_count + ", same_list:" + same_list.toString());

	}

	/**
	 * 55，效率最高，推荐
	 */
	@Test
	public void test0() {
		for_count = 0;
		same_count = 0;
		same_list.clear();
		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < str2.length; j++) {
				for_count++;
				if (str1[i].equals(str2[j])) {
					same_count++;
					same_list.add(str1[i]);
					break;
				}
			}
		}
		System.out.println(
				"for_count:" + for_count + ", same_count:" + same_count + ", same_list:" + same_list.toString());
	}

}
