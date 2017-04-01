package com.allen.common.thead.countdownlatch;

/**
 * Created by allen on 2017/3/10.
 * 测试代码去检测一下闭锁的功能
 *
 */
public class Main {
    public static void main(String[] args) {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }

}
