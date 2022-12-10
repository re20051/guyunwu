package com.example.guyunwu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.guyunwu.model.entity.User;

import java.util.Calendar;

public class JwtUtil {

    private static final String SING = "508nb";

    public static String getToken(User user) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 7);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("phoneNumber", user.getUsername())
                .withClaim("password", user.getPassword())
                .withClaim("id",user.getId());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
    }

    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}
