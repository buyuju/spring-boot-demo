package com.example.demo.config.mybatis;


public enum DataSourceType {
    read("read"), write("write");
    private String type;

    DataSourceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
