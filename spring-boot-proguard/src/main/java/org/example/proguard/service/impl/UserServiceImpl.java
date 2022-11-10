package org.example.proguard.service.impl;

import org.example.proguard.entity.AuthInfoAdmin;
import org.example.proguard.mapper.UserMapper;
import org.example.proguard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<AuthInfoAdmin> queryAuthInfoAdminByAttribute(AuthInfoAdmin authInfoAdmin) {
        return userMapper.selectAuthInfoAdminByAttribute(authInfoAdmin);
    }
}
