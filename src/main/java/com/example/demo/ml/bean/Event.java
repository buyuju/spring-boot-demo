package com.example.demo.ml.bean;

import java.util.Date;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/5/16 16:24
 * @Modified by :
 **/
public abstract class Event {
    public String title;
    public Date bornTime;

    public Event(String title) {
        this.title = title;
        this.bornTime = new Date();
    }
}
