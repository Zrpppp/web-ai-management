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
}
