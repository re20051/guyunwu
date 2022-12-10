package com.example.guyunwu.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.guyunwu.exception.BadRequestException;
import com.example.guyunwu.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String token = request.getHeader("Authorization");
        token = token == null ? null : token.replace("Bearer ", "");
        if (token == null || token.length() == 0 || "null".equals(token)) {
            throw new BadRequestException("未登录");
        }
        try {
            JwtUtil.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            throw new BadRequestException("无效签名");
        } catch (TokenExpiredException e) {
            throw new BadRequestException("token过期");
        } catch (AlgorithmMismatchException e) {
            throw new BadRequestException("token算法不一致");
        } catch (BadRequestException e) {
            throw new BadRequestException("无权限");
        } catch (Exception e) {
            throw new BadRequestException("token无效");
        }
    }
}
