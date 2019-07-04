package com.example.demo.service;

/**
 * @Author limeng
 * @Description 地址
 * @Date @Date: 2019/2/13 10:43
 * @Modified by :
 **/
public interface IUserService {

    /**
     * 登录
     * @param accessKey
     * @param accessSecret
     */
    void login(String accessKey, String accessSecret);
}
