package org.ratelimit.biz.strategy;

import lombok.Getter;
import org.ratelimit.biz.entity.BucketDetails;
import org.ratelimit.biz.entity.RequestDetails;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TokenBucketRateLimitingStrategyImpl implements RateLimitingStrategy {

    @Getter
    private final HashMap<String, BucketDetails> buckets;
    private static TokenBucketRateLimitingStrategyImpl INSTANCE;
    private final ExecutorService executor;

    private TokenBucketRateLimitingStrategyImpl() {
        this.buckets = new HashMap<>();
        executor = Executors.newSingleThreadExecutor();
        TokenBucketUpdateTokenJob job = new TokenBucketUpdateTokenJob(buckets);
        executor.submit(job);
    }

    @Override
    public boolean allow(RequestDetails request) {
        String key = generateKey(request);
        this.buckets.computeIfAbsent(key, k -> new BucketDetails(request));
        boolean result = this.buckets.get(key).decrementCounter();
        System.out.println(String.format("Taken a token for key: %s and token count: %d", key, this.buckets.get(key).getCounter()));
        return result;
    }

    public void close() {
        this.executor.shutdownNow();
    }


    private String generateKey(RequestDetails request) {
        return request.getService() + ":" + request.getIpAddress();
    }

    public static TokenBucketRateLimitingStrategyImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (TokenBucketRateLimitingStrategyImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TokenBucketRateLimitingStrategyImpl();
                }
            }
        }
        return INSTANCE;
    }

}
