package com.example.demo.common.enums;

/**
 * @Author limeng
 * @Description 告警配置枚举
 * @Date @Date: 2019/3/12 19:32
 * @Modified by :
 **/
public enum AlarmTypeEnum {
    /**
     * 告警枚举
     */
    ALARM_TYPE_1(1, "接单延迟"),
   ;

    /** 告警类型 */
    private Integer type;
    /** 告警名称 */
    private String name;

    private AlarmTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * 判断是否包含该type
     * @param type
     * @return
     */
    public static boolean containType(Integer type) {
        if (type == null) { return false;}
        for (AlarmTypeEnum c : AlarmTypeEnum.values()) {
            if (c.type.equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过type获取name
     * @param type
     * @return
     */
    public static String getNameByType(Integer type) {
        if (type == null) { return null;}
        for (AlarmTypeEnum c : AlarmTypeEnum.values()) {
            if (c.type.equals(type)) {
                return c.name;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

