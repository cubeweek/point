package com.musinsa.payment.point.free.controller;

import com.musinsa.payment.point.common.exception.PointValidationException;
import com.musinsa.payment.point.free.model.Point;
import com.musinsa.payment.point.free.service.FreePointService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/free-point")
public class FreePointController {
    private final FreePointService service;

    public FreePointController(FreePointService service) {
        this.service = service;
    }

    @GetMapping("/{memberId}")
    public int getUsablePoint(@PathVariable String memberId) {
        return service.selectUsablePoint(memberId);
    }


    @PostMapping("/earn")
    public int earn(@RequestBody Point p) {
        if (p.getMemberId() == null) throw new PointValidationException("포인트를 적립할 회원 정보를 먼저 입력해주세요.");
        if (p.getAmt() < 0) throw new PointValidationException("적립할 포인트 금액이 유효하지 않습니다.");

        return service.earnPoint(p);
    }

    @DeleteMapping("/earn")
    public int cancelEarn(@RequestBody Point p) {
        if (p.getPointSrl() == 0) throw new PointValidationException("적립 취소할 적립 이력이 입력되지 않았습니다.");

        return service.cancelEarnPoint(p);
    }

    @PostMapping("/use")
    public int use(@RequestBody Point p) {
        if (p.getMemberId() == null) throw new PointValidationException("적립 포인트를 사용할 회원 정보를 먼저 입력해주세요.");
        if (p.getBuySrl() < 0) throw new PointValidationException("적립 포인트를 사용할 주문 정보를 먼저 입력해주세요.");
        if (p.getAmt() < 0) throw new PointValidationException("사용할 포인트 금액이 0원 이하입니다.");

        return service.usePoint(p);
    }

    @DeleteMapping("/use")
    public int cancelUse(@RequestBody Point p) {
        if (p.getPointSrl() == 0) throw new PointValidationException("사용 취소할 적립 이력이 입력되지 않았습니다.");

        return service.cancelUsePoint(p);
    }
}
