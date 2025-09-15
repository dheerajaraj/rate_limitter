package org.ratelimit.biz.strategy;

import org.ratelimit.biz.entity.RequestDetails;

public interface RateLimitingStrategy {
    boolean allow(RequestDetails request);
    void close();
}
