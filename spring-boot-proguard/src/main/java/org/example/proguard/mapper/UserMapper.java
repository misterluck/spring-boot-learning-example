package org.example.proguard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.proguard.entity.AuthInfoAdmin;

import java.util.List;

@Mapper
public interface UserMapper {

    List<AuthInfoAdmin> selectAuthInfoAdminByAttribute(AuthInfoAdmin authInfoAdmin);

}
