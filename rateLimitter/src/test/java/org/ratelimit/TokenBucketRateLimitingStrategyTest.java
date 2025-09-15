package org.ratelimit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.ratelimit.biz.entity.RequestDetails;
import org.ratelimit.biz.strategy.TokenBucketRateLimitingStrategyImpl;

import java.util.HashMap;
import java.util.LinkedList;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TokenBucketRateLimitingStrategyTest {

    TokenBucketRateLimitingStrategyImpl tokenBucketRateLimitingStrategyImpl;


    /*@BeforeEach
    public void setup() {
        this.tokenBucketRateLimitingStrategyImpl = TokenBucketRateLimitingStrategyImpl.getInstance();
    }

    @Test
    public void testRateLimit() throws InterruptedException {
        boolean result = this.tokenBucketRateLimitingStrategyImpl.allow(new RequestDetails("REQ001", "serviceA", "196.0.0.1", "dheeraj1994"));
        Assertions.assertTrue(result);
        HashMap<String, LinkedList<RequestDetails>> buckets = this.tokenBucketRateLimitingStrategyImpl.getBuckets();
        Assertions.assertEquals(1, buckets.size());
        LinkedList<RequestDetails> list = buckets.get("serviceA:196.0.0.1");
        Assertions.assertEquals(1, list.size());
        RequestDetails detail = list.get(0);
        Assertions.assertEquals("dheeraj1994", detail.getUserId());
        Thread.sleep(300);
        this.tokenBucketRateLimitingStrategyImpl.allow(new RequestDetails("REQ002", "serviceA", "196.0.0.1", "dheeraj1994"));
        Assertions.assertTrue(result);
        Assertions.assertEquals(1, buckets.size());
        Assertions.assertEquals(2, list.size());
        result = this.tokenBucketRateLimitingStrategyImpl.allow(new RequestDetails("REQ003", "serviceA", "196.0.0.1", "dheeraj1994"));
        Assertions.assertFalse(result);
        Thread.sleep(2000);
        result = this.tokenBucketRateLimitingStrategyImpl.allow(new RequestDetails("REQ003", "serviceA", "196.0.0.1", "dheeraj1994"));
        Assertions.assertTrue(result);
        Assertions.assertEquals(1, list.size());
        this.tokenBucketRateLimitingStrategyImpl.getBuckets().clear();

    }

    @Test
    public void testAfterRequestProcess() {
        this.tokenBucketRateLimitingStrategyImpl.allow(new RequestDetails("REQ001", "serviceA", "196.0.0.1", "dheeraj1994"));
        HashMap<String, LinkedList<RequestDetails>> buckets = this.tokenBucketRateLimitingStrategyImpl.getBuckets();
        Assertions.assertEquals(0, buckets.size());
    }*/


}
