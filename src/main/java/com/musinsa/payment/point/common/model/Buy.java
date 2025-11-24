package com.musinsa.payment.point.common.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Buy {
    private long buySrl;
    private String memberId;
    private String payAmt;
    private LocalDateTime buyDt;
    private LocalDateTime payDt;
}
