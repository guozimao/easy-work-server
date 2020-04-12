package com.maozi.easywork.controller;

import com.alibaba.fastjson.JSONObject;
import com.maozi.easywork.common.annotations.UserLoginToken;
import com.maozi.easywork.entity.ResposeEntity;
import com.maozi.easywork.entity.User;
import com.maozi.easywork.service.TokenService;
import com.maozi.easywork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    TokenService tokenService;
    //登录
    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestBody User user){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findOneByUserName(user.getUserName());
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getPassWord().equals(user.getPassWord())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                ResposeEntity resposeEntity = new ResposeEntity(jsonObject);
                return resposeEntity;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getPermission")
    @ResponseBody
    public Object getPermission(@RequestHeader(name = "token") String token){
        Integer userId = Integer.valueOf(tokenService.getUserId(token));
        Map permission = userService.getPermission(userId);
        ResposeEntity resposeEntity = new ResposeEntity(permission);
        return resposeEntity;
    }

}
