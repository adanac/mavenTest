package com.allen.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class ListTest {
	@Test
	public void test_remove_add_All() throws Exception {
		List<String> list1 = new ArrayList<String>();
		list1.add("1111");
		list1.add("2222");
		list1.add("3333");

		List<String> list2 = new ArrayList<String>();
		list2.add("3333");
		list2.add("4444");
		list2.add("5555");
		// 无重复并集
		list2.removeAll(list1);
		list1.addAll(list2);

		Iterator<String> it = list1.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void test_removeAll() throws Exception {
		List<String> list1 = new ArrayList<String>();
		list1.add("1111");
		list1.add("2222");
		list1.add("3333");

		List<String> list2 = new ArrayList<String>();
		list2.add("3333");
		list2.add("4444");
		list2.add("5555");
		// 并集
		System.out.println(list1.size());
		list1.addAll(list2);
		System.out.println(list1.size());
	}

	@Test
	public void test_addAll() throws Exception {
		List<String> list1 = new ArrayList<String>();
		list1.add("1111");
		list1.add("2222");
		list1.add("3333");

		List<String> list2 = new ArrayList<String>();
		list2.add("3333");
		list2.add("4444");
		list2.add("5555");
		System.out.println(list1.size());
		// 差集
		list1.removeAll(list2);
		System.out.println(list1.size());
	}

	/**
	 * 当两个数据只有一个时，就会有bug
	 * @throws Exception
	 */
	@Test
	public void test_retainAll_bug() throws Exception {
		List<String> list1 = new ArrayList<String>();
		list1.add("1111");
		list1.add("2222");
		list1.add("3333");
		list1.add("4444");

		List<String> list2 = new ArrayList<String>();
		list2.add("5555");
		list2.add("7777");
		list2.add("3333");
		list2.add("6666");
		// 交集
		// System.out.println(list2.retainAll(list1));// false
		// System.out.println(list1.retainAll(list2));// false
		int count = 0;
		boolean contain = false;
		for (String str1 : list1) {
			for (String str2 : list2) {
				count++;
				if (str1.equals(str2)) {
					contain = true;
					break;
				}
			}
		}

		System.out.println(count + "," + contain);
	}

	@Test
	public void test_retainAll() throws Exception {
		List<String> list1 = new ArrayList<String>();
		list1.add("1111");
		list1.add("2222");
		list1.add("3333");

		List<String> list2 = new ArrayList<String>();
		list2.add("3333");
		list2.add("4444");
		list2.add("5555");
		System.out.println(list1.size());
		// 交集
		// list1.retainAll(list2);
		System.out.println(list2.retainAll(list1));
		System.out.println(list1.size());

	}

	@Test
	public void test_retainAll2() {
		List<String> list1 = new ArrayList<String>();
		list1.add("8017");
		addToList(list1, "8017");
		addToList(list1, "8000");

		List<String> list2 = new ArrayList<String>();
		list2.add("8017");
		System.out.println(list2.retainAll(list1));
		System.out.println(list1.retainAll(list2));
	}

	private void addToList(List<String> list1, String string) {
		if (!list1.contains(string)) {
			list1.add(string);
		}
	}

	public static void main(String[] args) {
		// Map<Integer, String> map = new HashMap<Integer, String>();
		// List<String> list1 = initList1();
		// List<Integer> list2 = initList2();
		// for (int i = 0; i < list1.size(); i++) {
		// map.put(i, list1.get(i) + "," + list2.get(i));
		// }
		// System.out.println(map.toString());
		test1();
	}

	private static List<Integer> initList2() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		return list;
	}

	private static List<String> initList1() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		return list;
	}

	public static void test1() {
		List<Object> list = new ArrayList<Object>();
		List<String> listIn = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			listIn.add(i + "00" + i);
		}
		list.add(new Boolean(true));
		list.add(listIn);
		System.out.println(list.get(0));
		System.out.println(list.get(1));

	}

}
