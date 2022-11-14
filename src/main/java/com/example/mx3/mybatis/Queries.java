package com.example.mx3.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * 查询器
 *
 * @author yunong.byn
 * @since 2021/7/13 4:47 下午
 */
public class Queries {

    public static <T> QueryWrapper<T> emptyWrapper() {
        return Wrappers.emptyWrapper();
    }

    public static <T> LambdaQuery<T> with(Class<T> cls) {
        return new LambdaQuery<>(cls);
    }

    public static <T> LambdaQuery<T> with(T entity) {
        return new LambdaQuery<>(entity);
    }

}
