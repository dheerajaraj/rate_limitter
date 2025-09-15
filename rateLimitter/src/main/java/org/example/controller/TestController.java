package org.example.controller;

import org.ratelimit.biz.entity.RequestDetails;


public class TestController implements Controller {

    private static TestController INSTANCE;
    private TestController() {}
    public void testMyInterceptor(RequestDetails requestDetails) {

    }

    public static TestController getINSTANCE() {
        if(INSTANCE == null){
            synchronized (TestController.class){
                if(INSTANCE == null){
                    INSTANCE = new TestController();
                }
            }
        }
        return INSTANCE;
    }
}
