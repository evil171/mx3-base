package com.example.mx3.utils;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mx3.mapper.OptionalMapper;
import lombok.var;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GroupByUtil {

    public <T> Map<String, List<Object>> get(Mapper<T> mapper, String... columns) {
        Map<String, List<Object>> groupByMap = new LinkedHashMap<>(columns.length);
        for (String column : columns) {
            column = StrUtil.toUnderlineCase(column);
            var queries = Wrappers.<T>query()
                    .select(column)
                    .groupBy(column);
            List<Map<String, Object>> itemList;
            if (mapper instanceof OptionalMapper) {
                OptionalMapper<T> optionalMapper = (OptionalMapper<T>) mapper;
                itemList = optionalMapper.selectMaps(queries);
            } else {
                BaseMapper<T> baseMapper = (BaseMapper<T>) mapper;
                itemList = baseMapper.selectMaps(queries);
            }
            List<Object> resultList = new ArrayList<>(itemList.size());
            for (Map<String, Object> map : itemList) {
                var value = map.get(column);
                if (value != null) {
                    resultList.add(value);
                }
            }
            groupByMap.put(StrUtil.toCamelCase(column), resultList);
        }
        return groupByMap;
    }

}
