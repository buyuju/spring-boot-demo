package com.example.demo.dao.mapper;


import com.example.demo.dao.entity.User;

import java.util.List;

/**
 * @author lm
 * user 模块
 */
public interface UserMapper {
    /**
     * 用户名查找
     * @param accessKey
     * @return
     */
    List<User> getUserByAccessKey(String accessKey);
}