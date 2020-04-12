package com.maozi.easywork.mapper;

import com.maozi.easywork.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper
{
    User findOne(int id);

    List<User> findAll();

    User findOneByUserName(String userName);

    Map getPermission(Integer userId);
}
