package com.example.demo.ml.impl;

import com.example.demo.ml.WeChatService;
import com.example.demo.ml.bean.Event;
import com.example.demo.ml.bean.User;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/5/16 16:23
 * @Modified by :
 **/
public class DefaultWeChatService {

    private String godName = "孟立";

    /**
     * 订阅者们
     */
    private List<User> followerList = new ArrayList<>();

    public void publish(Event event) {
        System.out.println(String.format("公众号于%s时分推出标题为%s的文章",
                event.bornTime,
                event.title));
        if (CollectionUtils.isNotEmpty(followerList)) {
            // 通知订阅者
            followerList.stream().forEach(follower ->{
                noticeFollower(follower, event.title);
            });
        }
    }

    /**
     * 通知订阅者
     * @param follower
     * @param title
     */
    private void noticeFollower(User follower, String title) {
        System.out.println(String.format("通知用户{%s},公众号出新攻略【%s】",
                follower.name,
                title));
    }

    /**
     * add follower
     * @param user
     */
    public void addFollower(User user) {
        followerList.add(user);
        System.out.println(String.format("成功获得新的追求者{%s}！",
                user.name));
    }

    /**
     * add follower
     * @param user
     */
    public void login(User user) {
        System.out.println(String.format("欢迎用户{%s}上线！",
                user.name));
        if (godName.equals(user.name)) {
            followerList.stream().forEach(follow ->{
                // 王者登录，通知所有弟弟们
                System.out.println(String.format("通知用户{%s},大家一起为王者【%s】欢呼吧！",
                        follow.name,
                        user.name));
            });
        }
    }
}
