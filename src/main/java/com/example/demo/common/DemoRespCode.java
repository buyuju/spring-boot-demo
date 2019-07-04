package com.example.demo.common;


/**
 *  商城返回码
 */
public enum DemoRespCode {

    /** 成功 */
    _000000(0, "操作成功"),
    _066666(66666,"请求失败"),
    /** 参数不能为空 */
    _010900(10900,"参数为空"),
    _010901(10901,"参数错误"),

    _010000(10000, "用户或者密码不正确"),


    ;



    private Integer code;
    private String desc;

    private DemoRespCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
