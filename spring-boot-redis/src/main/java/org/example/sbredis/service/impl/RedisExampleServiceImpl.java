package org.example.sbredis.service.impl;

import org.example.sbredis.service.RedisExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhaol on 2018/5/2.
 */
@Service
public class RedisExampleServiceImpl implements RedisExampleService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void save() {
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        SetOperations<String, Object> setOps = redisTemplate.opsForSet();
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();

        //valueOps.set("key","value");
        //valueOps.get("key");

        //listOps.leftPush("key", "leftValue");
        //listOps.leftPushAll("key", "leftValue1", "leftValue2");
        //listOps.rightPush("key", "rightValue");
        //listOps.rightPushAll("key", "rightValue1", "rightValue2");
        //获取指定key的范围内的value值的 list列表（0 -1）反回所有值列表
        //List<Object> list = listOps.range("key", 0, listOps.size("key"));
        //移除列表左边的第一个值
        //listOps.leftPop("key");
        //移除列表右边的第一个值
        //listOps.rightPop("key");

        redisTemplate.delete("key");
        //为散列添加或者覆盖一个hashKey-hashValue键值对
        hashOps.put("key", "hashKey0", "hashValue0");
        //为散列添加多个key-value键值对
        Map<String, String> map = new HashMap<String, String>();
        map.put("hashKey1", "hashValue1");
        map.put("hashKey2", "hashValue2");
        map.put("hashKey3", "hashValue3");
        hashOps.putAll("key", map);
        //获取散列的value集合
        List<Object> valueList = hashOps.values("key");
        for (Object obj : valueList) {
            System.out.println(obj);
        }
        System.out.println("#####################################");
        //获取散列的key-value键值对集合
        Map<String, Object> hashMap = hashOps.entries("key");
        Set<String> hashKey = hashMap.keySet();
        for (String key: hashKey) {
            System.out.println("hashKey:"+key+" hashValue: "+hashMap.get(key));
        }
        System.out.println("#####################################");
        //得到某个散列中hashKey的hashValue值
        String hashValue = (String) hashOps.get("key", "hashKey0");
        System.out.println("hashKey0-->hashValue0: "+hashValue);

        /*
        * 第二组API只是在第一组API的上面将key值的绑定放在获得接口时了，此举方便了每次操作
        * 基本数据类型的时候不用反复的去填写key值，只需要操作具体的value就行了。
        * */
        /*BoundValueOperations<String, Object> boundValueOps = redisTemplate.boundValueOps("key");
        BoundListOperations<String, Object> boundListOps = redisTemplate.boundListOps("key");
        BoundSetOperations<String, Object> boundSetOps = redisTemplate.boundSetOps("key");
        BoundZSetOperations<String, Object> boundZSetOps = redisTemplate.boundZSetOps("key");
        BoundHashOperations<String, Object, Object> boundHashOps = redisTemplate.boundHashOps("key");*/
    }

    @Override
    public void saveList() {
        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        listOps.leftPush("list", "one");
        listOps.leftPush("list", "two");
        listOps.leftPush("list", "three");
        listOps.leftPush("list", "four");

        List<Object> list = listOps.range("list", 0, -1);
        for (Object item : list) {
            System.out.println(item);
        }
    }

    @Override
    public void removeList() {
        /*ListOperations<String, Object> listOps = redisTemplate.opsForList();
        List<Object> after = listOps.range("list", 0, -1);
        System.out.println(after.size());
        listOps.remove("list", 0, "one");
        List<Object> before = listOps.range("list", 0, -1);
        System.out.println(before.size());*/

        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        long timestamp = 1531804832123L;
        // listOps.remove("list", 0, new User("姓名1", "12", "男", new Date(timestamp)));
    }

    @Override
    public void show() {
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        SetOperations<String, Object> setOps = redisTemplate.opsForSet();
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();

        System.out.println("ENCODING:"+valueOps.get("10_19_19_39_3306_ENCODING"));
        System.out.println("IGNORE_CASE:"+valueOps.get("10_21_171_14_3306_IGNORE_CASE"));
        Map<String, Object> studendMap = hashOps.entries("10_21_171_18_3306_2310_TABLE");
        Set<String> studendKey = studendMap.keySet();
        for (String key: studendKey) {
            System.out.println("hashKey:"+key+" hashValue: "+studendMap.get(key));
        }

        System.out.println("#####################################");
        //获取散列的value集合
        List<Object> valueList = hashOps.values("key");
        for (Object obj : valueList) {
            System.out.println(obj);
        }
        System.out.println("#####################################");
        //获取散列的key-value键值对集合
        Map<String, Object> hashMap = hashOps.entries("key");
        Set<String> hashKey = hashMap.keySet();
        for (String key: hashKey) {
            System.out.println("hashKey:"+key+" hashValue: "+hashMap.get(key));
        }
        System.out.println("#####################################");
        //得到某个散列中hashKey的hashValue值
        String hashValue = (String) hashOps.get("key", "hashKey0");
        System.out.println("hashKey0-->hashValue0: "+hashValue);
    }

    @Override
    public void trim() {
        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        //System.out.println(listOps.size("key"));
        //listOps.trim("key", 0, 0);
        //listOps.rightPop("key");
        //listOps.leftPop("key");
    }

    public byte[] serialize(Object t) {

        return null;
    }

}
