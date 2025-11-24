package com.musinsa.payment.point.free.service;

import com.musinsa.payment.point.common.exception.PointValidationException;
import com.musinsa.payment.point.free.model.Point;
import com.musinsa.payment.point.free.repository.FreePointRepository;
import com.musinsa.payment.point.policy.model.PointPolicy;
import com.musinsa.payment.point.policy.model.PointPolicyPersonal;
import com.musinsa.payment.point.policy.repository.PointPolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FreePointService {
    private final FreePointRepository repository;
    private final PointPolicyRepository policyRepository;

    public int selectUsablePoint(String memberId) {
        return repository.selectUsablePointAmt(memberId);
    }

    public int earnPoint(Point p) {
        PointPolicy maxLimit = policyRepository.selectPointPolicy("ONE_TIME_MAX_LIMIT");
        if (!p.isRemakeFlag() && (0 >= p.getAmt() || maxLimit.getAmt() < p.getAmt())) {
            throw new PointValidationException("1회 적립 가능 포인트 범위를 벗어났습니다 (1회 당 최대 가능 포인트 : " + maxLimit.getAmt() + ")");
        }
        p.setExpirationDate(LocalDate.now().plusDays(maxLimit.getExpirationDays()));

        int policyLimit = policyRepository.selectPointPolicy("FREE_POINT_PERSONAL").getAmt();

        int usablePoint = repository.selectUsablePointAmt(p.getMemberId());
        PointPolicyPersonal policyPersonal = policyRepository.selectPointPolicyPersonal(p.getMemberId());
        if (policyPersonal != null) policyLimit = policyPersonal.getMaxLimit();
        if (usablePoint + p.getAmt() > policyLimit) {
            int remainEarnAmt = policyLimit - usablePoint - p.getAmt();

            if (remainEarnAmt > 0) log.info("최대 보유 포인트 금액을 초과하여 차액만 적립됩니다. : {}+{} > {} => {} ", usablePoint, p.getAmt(), policyLimit, remainEarnAmt);
            else throw new PointValidationException("최대 보유 포인트 금액을 초과하여 적립 불가합니다.");
            p.setAmt(remainEarnAmt);
        }

        return repository.insertPoint(p);
    }

    public int cancelEarnPoint(Point p) {
        Point cancelTarget = repository.selectPointByPointSrl(p.getPointSrl());
        if (!"EARN".equals(cancelTarget.getType())) throw new PointValidationException("해당 내역은 적립 건이 아닙니다.");
        if (cancelTarget.isCancelYn()) throw new PointValidationException("이미 적립 취소되었습니다.");
        else if (cancelTarget.getUsedAmt() > 0) throw new PointValidationException("일부 사용된 경우 적립 취소가 불가합니다.");

        return repository.updatePoint(p);
    }

    public int usePoint(Point p) {
        int successCnt = 0, useAmt = p.getAmt();

        int usablePoint = repository.selectUsablePointAmt(p.getMemberId());
        if (usablePoint < useAmt) throw new PointValidationException("사용 가능한 포인트를 초과했습니다. 사용 가능 포인트:" + usablePoint);

        repository.insertUsePoint(p);
        long useSrl = p.getPointSrl();
        List<Point> list = repository.selectUsablePointList(p.getMemberId());

        for (Point l : list) {
            int calcUseAmt = Math.min(l.getAmt() - l.getUsedAmt(), useAmt);
            l.setUpdateBy(useSrl);
            l.setUsedAmt(l.getUsedAmt() + calcUseAmt);
                useAmt -= calcUseAmt;
                successCnt++;
                if (useAmt == 0) break;
        }
        if (useAmt == 0) return successCnt;
        else {
            throw new PointValidationException("일부 사용에 실패한 적립내역이 존재합니다. 요청금액:" + p.getAmt() + ", 사용실패금액:" + useAmt);
        }
    }

    public int cancelUsePoint(Point p) {
        int successCnt = 0, cancelAmt = p.getAmt();
        long useSrl = p.getPointSrl();

        Point originPoint = repository.selectPointByPointSrl(useSrl);
        if (originPoint.getAmt() - originPoint.getUsedAmt() < cancelAmt) {
            throw new PointValidationException("현재 사용 취소 가능한 적립금을 초과하였습니다.");
        } else if (originPoint.isCancelYn()) {
            throw new PointValidationException("");
        }
        repository.insertCancelUsePoint(p);
        long cancelSrl = p.getPointSrl();
        originPoint.setUpdateBy(cancelSrl);
        originPoint.setUsedAmt(originPoint.getUsedAmt() + cancelAmt);
        repository.updateUsePoint(originPoint);

        List<Point> list = repository.selectUsedPointListByPointSrl(useSrl);
        LocalDate today = LocalDate.now();
        for (Point l : list) {
            int calcCancelAmt = Math.min(l.getUsedAmt(), cancelAmt);
            l.setUpdateBy(cancelSrl);
            l.setUsedAmt(l.getUsedAmt() - calcCancelAmt);
            if (repository.updateCancelUsePoint(l) > 0) {
                cancelAmt -= calcCancelAmt;
                successCnt++;
                if (cancelAmt == 0) break;
            }
        }
        if (cancelAmt > 0) {
            Point makeNewPoint = Point.builder()
                                      .amt(cancelAmt)
                                      .memberId(p.getMemberId())
                                      .createBy(cancelSrl)
                                      .remakeFlag(true)
                                      .build();
            earnPoint(makeNewPoint);
            successCnt++;
        }

        return successCnt;
    }
}
