package com.allen.common.thead.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by allen on 2017/3/10.
 */
public class CacheHealthChecker extends BaseHealthChecker {
    public CacheHealthChecker(CountDownLatch latch) {
        super("CacheService",latch);
    }

    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(8000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
