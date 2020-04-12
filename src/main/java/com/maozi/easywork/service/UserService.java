package com.maozi.easywork.service;

import com.maozi.easywork.entity.User;
import com.maozi.easywork.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User findOne(int id)
    {
        return userMapper.findOne(id);
    }

    public User findOneByUserName(String userName) { return userMapper.findOneByUserName(userName);}

    public List<User> findAll()
    {
        return userMapper.findAll();
    }

    public Map getPermission(Integer userId) {
        return userMapper.getPermission(userId);
    }
}
