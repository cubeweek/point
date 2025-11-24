package com.musinsa.payment.point.policy.repository;

import com.musinsa.payment.point.policy.model.PointPolicy;
import com.musinsa.payment.point.policy.model.PointPolicyPersonal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointPolicyRepository {
    PointPolicy selectPointPolicy(String policyName);
    PointPolicyPersonal selectPointPolicyPersonal(String memberId);
}
