package com.example.mx3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author yunong.byn
 * @since 2021/2/9 8:29 下午
 */
@Mapper
@Repository
public interface AnySqlMapper {

    List<LinkedHashMap<String, Object>> selectList(String sql);

    LinkedHashMap<String, Object> selectOne(String sql);

}
