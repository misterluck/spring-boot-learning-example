package org.example.proguard.service;

import org.example.proguard.entity.AuthInfoAdmin;

import java.util.List;

public interface UserService {

    List<AuthInfoAdmin> queryAuthInfoAdminByAttribute(AuthInfoAdmin authInfoAdmin);

}
