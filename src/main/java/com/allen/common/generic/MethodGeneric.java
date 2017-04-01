package com.allen.common.generic;

import com.allen.common.dto.Pair;
import org.testng.annotations.Test;

/**
 * Created by allen on 2017/3/7.
 */
public class MethodGeneric {


    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

    public static <T extends Comparable> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++count;
        return count;
    }

    // TODO: 2017/3/7
    @Test
    public void test_countGreaterThan2() {
        People p1 = new People("allen", 22);
        People p2 = new People("frank", 25);
        Object[] eles = {p1, p2};
//        int res = MethodGeneric.countGreaterThan(eles, p3);
//        System.out.println(res);
    }


    @Test
    public void test_countGreaterThan() {
        int[] arr = {2, 8, 11, 9, 5, 19, 1, 3};
        int res = MethodGeneric.countGreaterThan(arr, 5);
        System.out.println(res);
    }

    public static int countGreaterThan(int[] arr, int i) {
        int count = 0;
        for (int a : arr) {
            if (a > i) ++count;
        }
        return count;
    }

    @Test
    public void test1() {
        Pair<Integer, String> p1 = new Pair<Integer, String>(1, "apple");
        Pair<Integer, String> p2 = new Pair<Integer, String>(2, "pear");
        boolean same = MethodGeneric.<Integer, String>compare(p1, p2);
        System.out.println(same);

        boolean compare = MethodGeneric.compare(p1, p2);
        System.out.println(compare);
    }
}
