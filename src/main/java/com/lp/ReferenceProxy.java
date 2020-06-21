package com.lp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @auther lp
 * @date 2020/6/21 0021 12:52
 */
@Component
public class ReferenceProxy implements BeanPostProcessor {
    @Autowired
    RemoteHandler remoteHandler;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field [] fields = bean.getClass().getDeclaredFields();
        for (Field field:fields) {
            //针对加了这个注解的，设置为一个代理的值
            if(field.isAnnotationPresent(LpReference.class)){
                field.setAccessible(true);
                Object proxy = Proxy.newProxyInstance(field.getType().getClassLoader(),new Class<?>[]{field.getType()},remoteHandler);
                try {
                    //对加了注解的加了一个代理，代理实现了remoteHandler
                    field.set(bean,proxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }
        return bean;
    }
}
