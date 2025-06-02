package com.example.controller;

import com.example.pojo.Result;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.pojo.pageResult;
import com.example.servive.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 获取所有学生
     */
     @GetMapping("/list")
       public Result list() {
         log.info("查询所有");
         List<Student> studentList = studentService.list();
         return Result.success(studentList);
     }

    /**
     * 分页查询
     */
     @GetMapping
      public Result page(StudentQueryParam studentQueryParam) {
          log.info("分页查询，参数：{}",studentQueryParam);
          pageResult<Student> pageResult = studentService.page(studentQueryParam);
           return Result.success(pageResult);
     }
}
