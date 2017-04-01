package com.allen.common.collection.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListUniqueTest {

	public static void main(String[] args) {
		List<String> arr = new ArrayList<String>();
		arr.add("aaa");
		arr.add("bbb");
		arr.add("aaa");
		arr.add("ccc");
		arr = getNewList(arr); // 方法去重
		System.out.println(arr);

	}

	public static List<String> getNewList(List<String> li) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < li.size(); i++) {
			String str = li.get(i); // 获取传入集合对象的每一个元素
			if (!list.contains(str)) { // 查看新集合中是否有指定的元素，如果没有则加入
				list.add(str);
			} else {
				System.err.println("存在重复的Key:" + str);
			}
		}
		return list; // 返回集合
	}

	@Test
	public void removeDuplicate() {
		List<String> list = new ArrayList<String>();
		list.add("aa_00");
		list.add("aa_01");
		list.add("aa_02");
		list.add("aa_00");
		list.add("aa_02");
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}
		System.out.println(list);
	}

}
