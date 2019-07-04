package com.example.demo.ml;

import com.example.demo.ml.bean.Event;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/5/16 16:22
 * @Modified by :
 **/
public abstract class WeChatService {

    /**
     * 发布
     * @param event
     */
    abstract void publish(Event event);
}
