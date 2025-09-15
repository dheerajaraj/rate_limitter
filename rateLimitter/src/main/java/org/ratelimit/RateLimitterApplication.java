package org.ratelimit;

import org.example.controller.Controller;
import org.example.controller.TestController;
import org.ratelimit.biz.RateLimitIntercepter;
import org.ratelimit.biz.entity.RequestDetails;

import java.lang.reflect.Proxy;


public class RateLimitterApplication {

    public static void main(String[] args) throws InterruptedException {
        Controller controller = TestController.getINSTANCE();
        Controller proxyService = (Controller) Proxy.newProxyInstance(
                Controller.class.getClassLoader(),
                new Class[]{Controller.class},
                new RateLimitIntercepter(controller)
        );
        proxyService.testMyInterceptor(new RequestDetails("ID_001", "serviceA", "196.0.0.1", "dheeraj1994"));
        proxyService.testMyInterceptor(new RequestDetails("ID_002", "serviceA", "196.0.0.1", "dheeraj1994"));
        Thread.sleep(5000);
        proxyService.testMyInterceptor(new RequestDetails("ID_002", "serviceA", "196.0.0.1", "dheeraj1994"));
        Thread.sleep(5000);
        proxyService.testMyInterceptor(new RequestDetails("ID_002", "serviceA", "196.0.0.1", "dheeraj1994"));

    }
}
