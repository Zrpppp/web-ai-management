package com.example.servive.impl;

import com.example.mapper.StudentMapper;
import com.example.pojo.Emp;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.pojo.pageResult;
import com.example.servive.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     *  查询所有学生
     */
    @Override
    public List<Student> list() {
        return studentMapper.findAll();
    }

    /**
     *  分页查询
     */
    @Override
    public pageResult<Student> page(StudentQueryParam studentQueryParam) {
        //设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //执行查询
        List<Student> studentList = studentMapper.list(studentQueryParam);
        //解析查询结果并封装
        Page<Student> s = (Page<Student>) studentList;

        return new pageResult<Student>(s.getTotal(), s.getResult());
    }

    /**
     * 保存学生
     */
    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }
}
