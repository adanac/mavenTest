package com.allen.common.thead.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by allen on 2017/3/10.
 */
public class DatabaseHealthChecker extends BaseHealthChecker {
    public DatabaseHealthChecker(CountDownLatch latch) {
        super("DataService",latch);
    }

    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(9000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
