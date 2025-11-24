package com.musinsa.payment.point.policy.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PointPolicyPersonal {
    private String memberId;
    private int maxLimit;
    private LocalDateTime createDt;
}
