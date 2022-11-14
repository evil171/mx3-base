package com.example.mx3.bean;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mx3.utils.ConvertUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @since 2020/1/24 12:03 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResultBean<T> {

    private Integer current;

    private Integer size;

    private Integer total;

    private List<T> list;

    public static <T> PageResultBean<T> from(IPage<T> page) {
        return new PageResultBean<>(
                (int) page.getCurrent(),
                (int) page.getSize(),
                (int) page.getTotal(),
                page.getRecords()
        );
    }

    public static <T> PageResultBean<T> empty(PageParam page) {
        return new PageResultBean<>(
                page.getCurrent(),
                page.getSize(),
                0,
                Collections.emptyList()
        );
    }

    public static <T, R> PageResultBean<R> from(IPage<T> page, Function<T, R> convert) {
        return new PageResultBean<>(
                (int) page.getCurrent(),
                (int) page.getSize(),
                (int) page.getTotal(),
                ConvertUtil.from(page.getRecords(), convert)
        );
    }

    public static <T, R> PageResultBean<R> fromParallel(IPage<T> page, Function<T, R> convert) {
        return new PageResultBean<>(
                (int) page.getCurrent(),
                (int) page.getSize(),
                (int) page.getTotal(),
                ConvertUtil.fromParallel(page.getRecords(), convert)
        );
    }

    public static <T, R> PageResultBean<R> from(IPage<T> page, List<R> list) {
        return new PageResultBean<>(
                (int) page.getCurrent(),
                (int) page.getSize(),
                (int) page.getTotal(),
                list
        );
    }

}
