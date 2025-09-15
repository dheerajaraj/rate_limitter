package org.ratelimit.biz.strategy;

import org.ratelimit.biz.entity.BucketDetails;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class TokenBucketUpdateTokenJob implements Runnable {

    private final HashMap<String, BucketDetails> buckets;
    private long lastUpdateTime;

    TokenBucketUpdateTokenJob(HashMap<String, BucketDetails> buckets) {
        this.buckets = buckets;
        this.lastUpdateTime = Instant.now().toEpochMilli();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                if (this.lastUpdateTime < (Instant.now().toEpochMilli() - 3 * 1000)) {
                    for (Map.Entry<String, BucketDetails> entry : buckets.entrySet()) {
                        entry.getValue().refillCounter();
                    }
                    this.lastUpdateTime = Instant.now().toEpochMilli();
                }
            } catch (InterruptedException e) {
                this.buckets.clear();
                return;
            }
        }
    }
}
