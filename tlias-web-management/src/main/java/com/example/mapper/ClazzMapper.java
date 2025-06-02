package com.example.mapper;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     *  分页查询查询
     */
    public List<Clazz> list(ClazzQueryParam clazzQueryParam);
}
