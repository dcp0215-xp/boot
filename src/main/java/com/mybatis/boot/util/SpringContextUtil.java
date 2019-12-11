package com.mybatis.boot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author LX
 * @Date 2019/12/4 15:46
 * @Description TODO
 */

@Component
@Slf4j
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
       log.info("-------------------------------------------------------------------------------------------------------------------");


        log.info("========ApplicationContext���óɹ�,����ͨ�����ͨ������SpringUtils.getAppContext()��ȡapplicationContext����========");
//        System.out.println("========applicationContext=" + SpringContextUtil.applicationContext + "========");

       log.info("-------------------------------------------------------------------------------------------------------------------");
    }

    //��ȡapplicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //ͨ��name��ȡ Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //ͨ��class��ȡBean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //ͨ��name,�Լ�Clazz����ָ����Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
