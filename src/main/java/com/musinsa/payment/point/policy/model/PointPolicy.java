package com.musinsa.payment.point.policy.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PointPolicy {
    private long policySrl;
    private String policyName;
    private String description;
    private int amt;
    private int expirationDays;
    private LocalDate expirationDate;
}