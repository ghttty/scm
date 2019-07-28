package com.fantaike.scm.config.datasource;

import com.fantaike.scm.constants.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class DynamicDataSourceAspect {


    @Before("@annotation(Dynamic)")
    public void beforeSwitchDS(JoinPoint point){

        Class<?> className = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        DataSourceType dataSource = DataSourceType.Master;
        try {
            Method method = className.getMethod(methodName, argClass);

            if (method.isAnnotationPresent(Dynamic.class)) {
                Dynamic annotation = method.getAnnotation(Dynamic.class);
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataSourceContext.setDB(dataSource.getName());
    }


    @After("@annotation(Dynamic)")
    public void afterSwitchDS(JoinPoint point){

        DataSourceContext.clearDB();

    }
}