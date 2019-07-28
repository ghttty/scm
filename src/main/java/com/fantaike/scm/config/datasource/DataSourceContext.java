package com.fantaike.scm.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataSourceContext {
    public static final Logger log = LoggerFactory.getLogger(DataSourceContext.class);

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDB(String dbType) {
        log.debug("切换到{}数据源", dbType);
        contextHolder.set(dbType);
    }

    public static String getDB() {
        return (contextHolder.get());
    }

    public static void clearDB() {
        contextHolder.remove();
    }
}