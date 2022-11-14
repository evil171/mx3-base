package com.example.mx3.annotation;

import com.example.mx3.enums.BaseEnum;
import com.fasterxml.jackson.annotation.JacksonAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举信息序列化标识
 *
 * @author yunong.byn
 * @since 2021/8/10 11:11 下午
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface EnumMessage {

    Class<? extends BaseEnum<?>> type();

}
