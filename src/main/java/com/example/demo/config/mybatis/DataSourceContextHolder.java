package com.example.demo.config.mybatis;


public class DataSourceContextHolder {
    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {
        local.set(DataSourceType.read.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(DataSourceType.write.getType());
    }

    public static String getDataSourceType() {
        String type = local.get();
        if(type == null){
            write();
            type = local.get();
        }
        return type;
    }

    public static void clearDataSourceType(){
        local.remove();
    }
}
