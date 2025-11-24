package com.musinsa.payment.point.common.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Member {
    private String memberId;
    private String email;
    private LocalDate signupDate;
    private LocalDate withdrawalDate;

}
