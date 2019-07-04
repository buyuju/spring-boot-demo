package com.example.demo.service.impl;

import com.example.demo.common.DemoRespCode;
import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.exception.ClientException;
import com.example.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/2/13 10:41
 * @Modified by :
 **/
@Service("userService")
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * 登录
     * @param accessKey 用户名
     * @param accessSecret 密码
     */
    @Override
    public void login(String accessKey, String accessSecret) {
        User user = userRepository.getUserByAccessKey(accessKey);
        // 校验用户 密码
        if (user == null || !user.getAccessSecret().equals(accessSecret)) {
            throw new ClientException(DemoRespCode._010000);
        }
    }
}
