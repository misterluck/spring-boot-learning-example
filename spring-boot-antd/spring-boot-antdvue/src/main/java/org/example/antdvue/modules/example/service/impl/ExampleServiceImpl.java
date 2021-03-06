package org.example.antdvue.modules.example.service.impl;


import org.example.antdvue.modules.example.entity.Example;
import org.example.antdvue.modules.example.mapper.ExampleMapper;
import org.example.antdvue.modules.example.service.IExampleService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 测试单表
 * @Author:
 * @Date:   2019-12-25
 * @Version: V1.0
 */
@Service
public class ExampleServiceImpl extends ServiceImpl<ExampleMapper, Example> implements IExampleService {

}
