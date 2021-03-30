package org.example.sbredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LockRedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/deductStock", method = RequestMethod.POST)
    public String deductStock() {

        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
        if (stock > 0) {
            int realStock = stock - 1;
            stringRedisTemplate.opsForValue().set("stock", String.valueOf(realStock));
            System.out.println("扣减成功,剩余库存:"+realStock);
            return "扣减成功,剩余库存:"+realStock;
        }else {
            System.out.println("扣减失败,库存不足");
            return "扣减失败,库存不足";
        }
    }

}
