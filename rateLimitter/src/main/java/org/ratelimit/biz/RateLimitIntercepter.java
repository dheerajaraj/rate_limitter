package org.ratelimit.biz;

import org.ratelimit.biz.entity.RequestDetails;
import org.ratelimit.biz.strategy.RateLimitingStrategy;
import org.ratelimit.biz.strategy.TokenBucketRateLimitingStrategyImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RateLimitIntercepter implements InvocationHandler {

    private final Object target;
    private final RateLimitingStrategy strategy;

    public RateLimitIntercepter(Object target) {
        this.target = target;
        this.strategy = TokenBucketRateLimitingStrategyImpl.getInstance();
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestDetails details = (RequestDetails) args[0];
        this.strategy.allow(details);
        Object result = method.invoke(target, args); // Delegate to the real object
        System.out.println("After method: " + method.getName());
        return result;
    }
}
