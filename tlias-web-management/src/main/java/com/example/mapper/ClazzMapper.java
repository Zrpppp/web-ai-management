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

    /**
     *  添加班级
     */
    void add(Clazz clazz);

    /**
     *  批量删除班级
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id 查询班级
     */
    Clazz getById(Integer id);

    /**
     *  修改班级
     */
    void updateById(Clazz clazz);

    /**
     *  查询所有班级
     */

    List<Clazz> findAll();
}
