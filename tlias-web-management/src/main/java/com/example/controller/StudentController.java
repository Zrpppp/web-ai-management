package com.example.controller;

import com.example.pojo.Result;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.pojo.pageResult;
import com.example.servive.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增学生
     */
    @PostMapping
      public Result save(@RequestBody Student student) {
        log.info("新增学生{}",student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 批量删除学生
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除学生{}",ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }
}
