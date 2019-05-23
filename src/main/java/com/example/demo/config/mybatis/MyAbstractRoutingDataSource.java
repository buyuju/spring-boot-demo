package com.example.demo.config.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
    private final int dataSourceNumber;
    private AtomicInteger count = new AtomicInteger(0);

    public MyAbstractRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getDataSourceType();
        if (typeKey.equals(DataSourceType.write.getType())) {
            return DataSourceType.write.getType();
        }
        // 读简单负载均衡
        int number = count.getAndAdd(1);
        int lookupKey = number % dataSourceNumber;
        if(number > Integer.MAX_VALUE - 10000){
            count.set(1);
        }
        return Integer.valueOf(lookupKey);
    }

}