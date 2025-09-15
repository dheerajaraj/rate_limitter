package org.ratelimit.biz.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
public class RequestDetails {
    @EqualsAndHashCode.Exclude
    protected String requestId;
    protected String service;
    protected String ipAddress;
    protected String userId;
    @Setter
    @EqualsAndHashCode.Exclude
    protected Long ts;

    public RequestDetails(String requestId, String service, String ipAddress, String userId) {
        this.requestId = requestId;
        this.service = service;
        this.ipAddress = ipAddress;
        this.userId = userId;
        this.ts = Instant.now().toEpochMilli();
    }

}
