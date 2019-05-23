package com.example.demo.dao.repository;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.mapper.UserMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author limeng
 * @Description 用户
 * @Date @Date: 2019/2/13 11:12
 * @Modified by :
 **/
@Component("userRepository")
public class UserRepository {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户名查找 ，最多一个理论上
     * @param accessKey
     * @return
     */
    public User getUserByAccessKey(String accessKey) {
        List<User> list =  userMapper.getUserByAccessKey(accessKey);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

}
