package com.allen.common.generic;

import com.allen.common.dto.Box;
import org.junit.Test;

/**
 * Created by allen on 2017/3/7.
 */
public class BoxGeneric {
    @Test
    public void test1(){
        Box<Integer> integerBox = new Box<Integer>();
        Box<Double> doubleBox = new Box<Double>();
        Box<String> stringBox = new Box<String>();
        System.out.println("测试完成");
    }
}
