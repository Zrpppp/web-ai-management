package com.example.servive;

import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.pojo.pageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    /**
     * 查询所有
     */
    List<Student> list();

    /**
     * 分页查询
     */
    pageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 新增学生
     */
    void save(Student student);

    /**
     * 删除学生
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询学生
     */
    Student getInfo(Integer id);

    /**
     * 修改学生
     */
    void update(Student student);

    /**
     * 违规处理
     */
    void violation(Student student);
}
