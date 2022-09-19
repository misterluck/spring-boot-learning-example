package org.example.proguard.service.impl;

import org.example.proguard.entity.City;
import org.example.proguard.service.CityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Override
    public List<City> getCityAll() {
        List<City> list = new ArrayList<>();
        list.add(new City(0L, "10000", "中国", "国家", null));
        list.add(new City(1L, "10001", "北京", "首都", 0L));
        list.add(new City(2L, "10002", "上海", "", 0L));
        list.add(new City(3L, "10003", "广州", "", 0L));
        list.add(new City(4L, "10004", "深圳", "", 0L));
        list.add(new City(5L, "10005", "天津", "", 0L));
        list.add(new City(6L, "10006", "南京", "", 0L));
        list.add(new City(7L, "10007", "济南", "", 0L));
        list.add(new City(8L, "10008", "山东", "", 0L));
        list.add(new City(9L, "10009", "新疆", "", 0L));
        return list;
    }
}
