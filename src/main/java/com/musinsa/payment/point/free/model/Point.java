package com.musinsa.payment.point.free.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private long pointSrl;
    private String memberId;
    private long buySrl;
    private String type;
    private int amt;
    private int usedAmt;
    private LocalDate expirationDate;
    private boolean cancelYn;
    private boolean manualYn;
    private long createBy;
    private LocalDateTime createDt;
    private long updateBy;
    private LocalDateTime updateDt;

    private boolean remakeFlag;
}
