package com.example.mx3.utils;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ConvertUtil {

    private static volatile ThreadPoolTaskExecutor executor;

    public static <T, R> List<R> from(List<T> list, Function<T, R> function) {
        List<R> resultList = new ArrayList<>(list.size());
        for (T t : list) {
            resultList.add(function.apply(t));
        }
        return resultList;
    }

    public static <T, R> List<R> fromParallel(List<T> list, Function<T, R> function) {
        if (executor == null) {
            executor = SpringUtil.getBean("corePoolExecutor", ThreadPoolTaskExecutor.class);
        }
        List<CompletableFuture<R>> futureList = new ArrayList<>(list.size());
        for (T t : list) {
            futureList.add(
                    CompletableFuture.supplyAsync(
                            () -> function.apply(t),
                            executor
                    )
            );
        }
        List<R> resultList = new ArrayList<>(list.size());
        for (CompletableFuture<R> future : futureList) {
            resultList.add(future.join());
        }
        return resultList;
    }

}
