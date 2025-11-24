package com.musinsa.payment.point.policy.repository;

import com.musinsa.payment.point.free.model.Point;
import com.musinsa.payment.point.policy.model.PointPolicy;
import com.musinsa.payment.point.policy.model.PointPolicyPersonal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PointPolicyRepositoryTests {
    @Autowired
    private PointPolicyRepository repository;

    @Test
    public void 포인트정책_조회_테스트() {
        String policy_name = "ONE_TIME_MAX_LIMIT";
        PointPolicy result = repository.selectPointPolicy(policy_name);
        assertThat(result).isNotNull();
        assertThat(result.getPolicyName()).isEqualTo(policy_name);
    }

    @Test
    public void 개인별최대보유포인트_조회() throws Exception {
        PointPolicyPersonal result = repository.selectPointPolicyPersonal("adidaslover");
        assertThat(result).isNotNull();
        assertThat(result.getMaxLimit()).isEqualTo(300000);
    }

}
