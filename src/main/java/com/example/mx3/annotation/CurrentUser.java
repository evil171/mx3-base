package com.example.mx3.annotation;

import java.lang.annotation.*;

/**
 * date: 2018/10/23 10:26
 * content: 获取当前登录信息
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 强制
     */
    boolean required() default true;

}
