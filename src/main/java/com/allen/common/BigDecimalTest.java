package com.allen.common;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        System.out.println("7.22-7.02=" + (7.22f - 7.02f));// 7.22-7.02=0.19999981
        System.out.println("7.22-7.02=" + subtract(7.22, 7.02));// 7.22-7.02=0.2
    }

    /**
     * 精确的减法
     */
    public static double subtract(double d, double e) {
        BigDecimal b1 = new BigDecimal(Double.toString(d));
        BigDecimal b2 = new BigDecimal(Double.toString(e));
        return b1.subtract(b2).doubleValue();
    }

}
