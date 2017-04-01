package com.allen.common.collection.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by allen on 2017/3/8.
 */
public class HashSetTest {
    /**
     * 这是因为String类已经重写了equals()方法和hashcode()方法，所以hashset认为它们是相等的对象。
     */
    @Test
    public void test_Add() {
        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        Set hashset = new HashSet();
        hashset.add(s1);
        hashset.add(s2);
        Iterator it = hashset.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * hashset中添加了相同的元素，原因就在于我们自己写的Student类并没有重写自己的hashcode()和equals()方法。
     */
    @Test
    public void test_Add2(){
        HashSet hs = new HashSet();
        hs.add(new Student(1, "zhangsan"));
        hs.add(new Student(2, "lisi"));
        hs.add(new Student(3, "wangwu"));
        hs.add(new Student(1, "zhangsan"));

        Iterator it = hs.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    class Student {
        int num;
        String name;

        Student(int num, String name) {
            this.num = num;
            this.name = name;
        }

        public String toString() {
            return num + ":" + name;
        }

        public int hashCode() {
            return num * name.hashCode();
        }

        public boolean equals(Object o) {
            Student s = (Student) o;
            return num == s.num && name.equals(s.name);
        }
    }
}
