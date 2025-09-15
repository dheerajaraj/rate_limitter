package org.example.controller;

import org.ratelimit.biz.entity.RequestDetails;

public interface Controller {
    void testMyInterceptor(RequestDetails requestDetails);
}
