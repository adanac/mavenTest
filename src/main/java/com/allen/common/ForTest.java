package com.allen.common;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ForTest {

	private static final int COUNT = 10;

	@Test
	public void test1() throws Exception {
		String[] str1 = new String[COUNT];
		String[] str2 = new String[COUNT * COUNT];
		for (int i = 0; i < COUNT; i++) {
			str1[i] = "aa" + "_" + i;
		}

		for (int i = 0; i < COUNT * COUNT; i++) {
			str2[i] = "aa" + "_" + i;
		}

		int for_count = 0;
		int same_count = 0;
		List<String> same_list = new LinkedList<String>();
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
		System.err.println("----------------------------------------");

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
		System.err.println("----------------------------------------");

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
		System.err.println("----------------------------------------");

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
		System.err.println("----------------------------------------");

		/**
		 * for_count:1000, same_count:10, same_list:[aa_0, aa_1, aa_2, aa_3,
		 * aa_4, aa_5, aa_6, aa_7, aa_8, aa_9] for_count:955, same_count:10,
		 * same_list:[aa_0, aa_1, aa_2, aa_3, aa_4, aa_5, aa_6, aa_7, aa_8,
		 * aa_9] for_count:55, same_count:10, same_list:[aa_0, aa_1, aa_2, aa_3,
		 * aa_4, aa_5, aa_6, aa_7, aa_8, aa_9]
		 */
	}

}
