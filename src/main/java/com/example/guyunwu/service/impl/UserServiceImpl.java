package com.example.guyunwu.service.impl;

import com.example.guyunwu.model.entity.User;
import com.example.guyunwu.model.param.RegisterParam;
import com.example.guyunwu.repository.UserRepository;
import com.example.guyunwu.service.UserService;
import com.example.guyunwu.service.base.AbstractCrudService;
import com.example.guyunwu.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl extends AbstractCrudService<User, Long> implements UserService {

    protected UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    /**
     * 注册用户
     * @param param
     * @return
     */
    @Override
    public User register(RegisterParam param) {
        checkUniquePhoneNumber(param.getPhoneNumber());
        String encryptPassword = MD5Util.getMd5Code(param.getPassword());
        User user = new User();
        BeanUtils.copyProperties(param, user);
        user.setUsername(param.getPhoneNumber());
        user.setPassword(encryptPassword);
        return userRepository.save(user);
    }


    public void checkUniquePhoneNumber(String phoneNumber) {
        Assert.isTrue(userRepository.countByPhoneNumber(phoneNumber) == 0, "该手机号已被使用");
    }
}
