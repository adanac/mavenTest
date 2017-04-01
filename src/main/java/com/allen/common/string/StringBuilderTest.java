package com.allen.common.string;

import org.junit.Test;

/**
 * Created by allen on 2017/3/8.
 */
public class StringBuilderTest {

    @Test
    public void testDeleteCharAt(){
        int[] arr = {1,8,3,5};
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<arr.length;i++){
            sb.append(arr[i]+",");
        }
        if(sb.length()>0){
            sb.deleteCharAt(sb.length()-1);
        }
        System.out.println(sb.toString());
    }
}
