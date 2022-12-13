package com.example.guyunwu.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.guyunwu.model.dto.LoginDTO;
import com.example.guyunwu.model.dto.UserDTO;
import com.example.guyunwu.model.entity.User;
import com.example.guyunwu.model.param.LoginParam;
import com.example.guyunwu.model.param.RegisterParam;
import com.example.guyunwu.model.param.UpdateUserParam;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.UserService;
import com.example.guyunwu.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("注册新用户")
    @PostMapping(value = "/register")
    public Result register(@RequestBody @Validated RegisterParam registerParam) {
        User user = userService.register(registerParam);
        return Result.ok("ok", user);
    }

    @ApiOperation("登录")
    @PostMapping(value = "/login")
    public Result<LoginDTO> login(@RequestBody @Validated LoginParam loginParam, HttpServletRequest request) {
        User user = userService.login(loginParam);
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(user, loginDTO);
        loginDTO.setToken(JwtUtil.getToken(user));
        return Result.ok("ok", loginDTO);
    }

    @ApiOperation("更新用户信息")
    @PutMapping(value = "/update")
    public Result update(@RequestBody @Validated UpdateUserParam updateUserParam, HttpServletRequest request) {
        Long userId = JwtUtil.getUserId(request);
        User user = userService.update(updateUserParam, userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return Result.ok("ok", userDTO);
    }
}
