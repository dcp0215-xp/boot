package com.mybatis.boot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author LX
 * @Date 2019/12/12 13:59
 * @Description �Զ���ע�⣺ϵͳ��־ע��
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {
    String value() default "";
}
