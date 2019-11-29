package org.example.sbexa;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleTest {

    @Test
    public void test() {
        BigDecimal bigDecima1 = new BigDecimal(10);
        BigDecimal bigDecima2 = new BigDecimal(3);
        // BigDecimal bigDecima3 = bigDecima1.divide(bigDecima2, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecima1.divide(bigDecima2, 2, BigDecimal.ROUND_HALF_UP));
    }
}
