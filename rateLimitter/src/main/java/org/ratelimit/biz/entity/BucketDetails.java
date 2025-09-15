package org.ratelimit.biz.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
public class BucketDetails extends RequestDetails {

    private int counter;
    private static final int DEFAULT_UPPER_LIMIT_MINUTE = 2;


    public BucketDetails(RequestDetails requestDetails) {
        super(requestDetails.requestId, requestDetails.service, requestDetails.ipAddress, requestDetails.userId);
        this.counter = DEFAULT_UPPER_LIMIT_MINUTE;
    }

    public boolean decrementCounter() {
        synchronized (this) {
            if (this.counter == 0) {
                return false;
            }
            this.setTs(Instant.now().toEpochMilli());
            this.counter--;
            return true;
        }
    }

    /**
     * Refilled every minute
     */
    public void refillCounter() {
        if(this.counter == DEFAULT_UPPER_LIMIT_MINUTE) {
            return;
        }
        synchronized (this) {
                this.counter = DEFAULT_UPPER_LIMIT_MINUTE;
        }
    }
}
