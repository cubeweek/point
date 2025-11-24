package com.musinsa.payment.point.free.repository;

import com.musinsa.payment.point.free.model.Point;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FreePointRepositoryTests {
    @Autowired
    private FreePointRepository repository;

    @Test
    public void 무료포인트현황_조회() throws Exception {
        int result = repository.selectUsablePointAmt("adidaslover");
        assertThat(result).isEqualTo(10000);
    }

}
