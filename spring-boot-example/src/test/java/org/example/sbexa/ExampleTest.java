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

    @Test
    public void test1() {
        String str = "adfasd-fasdf.as,dfasd;fsadf";
        String[] strArr = str.split("[\\s-;,.]");
        for (int i=0; i<strArr.length; i++) {
            System.out.println(strArr[i]);
        }

    }

}
