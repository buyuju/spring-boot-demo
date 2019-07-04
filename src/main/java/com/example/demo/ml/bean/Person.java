package com.example.demo.ml.bean;

import com.example.demo.ml.impl.DefaultWeChatService;

/**
 * @Author limeng
 * @Description 个人用户
 * @Date @Date: 2019/5/16 16:53
 * @Modified by :
 **/
public class Person extends User{

    public Person(String name, Integer age) {
        super(name, age);
    }

    /**
     * 订阅公众号
     * @param weChat
     */
    @Override
    public void followWeChat(DefaultWeChatService weChat) {
        if (weChat == null) {
            throw new IllegalArgumentException("weChat is null");
        }
        weChat.addFollower(this);
    }

    /**
     * 登录
     * @param weChat
     */
    @Override
    public void login(DefaultWeChatService weChat) {
        if (weChat == null) {
            throw new IllegalArgumentException("weChat is null");
        }
        weChat.login(this);
    }
}
