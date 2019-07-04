package com.example.demo.exception;

/**
 * @author liulinbao
 * @date 2018/9/21
 */
public class Code {
    /**
     * config配置没找到
     */
    public static final Integer PRODUCER_CONFIG_NULL = 1000;
    /**
     * 找不到tag
     */
    public static final Integer PRODUCER_TAG_NULL = 1001;
    /**
     * 找不到topic
     */
    public static final Integer PRODUCER_TOPIC_NULL = 1002;
    /**
     * 找不到body
     */
    public static final Integer PRODUCER_BODY_NULL = 1003;


    /**
     * -------------------------------------------------------
     */
    /**
     * Consumer为空
     */
    public static final Integer CONSUMER_NULL = 1100;

    /**
     * config配置没找到
     */
    public static final Integer CONSUMER_CONFIG_NULL = 1101;
    /**
     * 找不到tag
     */
    public static final Integer CONSUMER_TAG_NULL = 1102;
    /**
     * 找不到topic
     */
    public static final Integer CONSUMER_TOPIC_NULL = 1103;
    /**
     * 没有配置消费者的handler
     */
    public static final Integer CONSUMER_HANDLER_NULL = 1104;

}
