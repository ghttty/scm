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


    //切点
    @Pointcut("execution(* com.fantaike.*.service..*.*(..)))")
    public void aspect() { }

    @Before("@annotation(Dynamic)")
    public void beforeSwitchDS(JoinPoint point){

        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();

        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        DataSourceType dataSource = DataSourceType.Master;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);

            if (method.isAnnotationPresent(Dynamic.class)) {
                Dynamic annotation = method.getAnnotation(Dynamic.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 切换数据源
        DataSourceContext.setDB(dataSource.getName());

    }


    @After("@annotation(Dynamic)")
    public void afterSwitchDS(JoinPoint point){

        DataSourceContext.clearDB();

    }
}