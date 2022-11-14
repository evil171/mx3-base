package com.example.mx3.annotation;

import java.lang.annotation.*;

/**
 * 字段注释
 *
 * @author yunong.byn
 * @since 2022/11/14 14:25
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface FieldComment {

    String value() default "";

}
