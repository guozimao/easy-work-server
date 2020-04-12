package com.maozi.easywork.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.maozi.easywork.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId().toString())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassWord()));// 以 password 作为 token 的密钥
        return token;
    }

    public String getUserId(String token){
        String userId = "";
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        return userId;
    }
}
