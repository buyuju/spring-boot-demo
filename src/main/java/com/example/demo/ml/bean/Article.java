package com.example.demo.ml.bean;


/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/5/16 17:16
 * @Modified by :
 **/
public class Article extends Event{

    private String linkUrl;

    public Article(String title) {
        super(title);
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
