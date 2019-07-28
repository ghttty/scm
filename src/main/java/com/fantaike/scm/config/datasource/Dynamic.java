package com.fantaike.scm.config.datasource;

import com.fantaike.scm.constants.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface Dynamic {
    DataSourceType value() default DataSourceType.Master;
}