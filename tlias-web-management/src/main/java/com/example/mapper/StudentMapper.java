package com.example.mapper;

import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     *  查询所有学生
     */
    List<Student> findAll();

    /**
     *  分页查询
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 新增学生
     */
    void add(Student student);

    /**
     * 批量删除学生
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询学生信息
     */
    Student getInfoById(Integer id);

    /**
     * 修改学生信息
     */
    void update(Student student);
}
