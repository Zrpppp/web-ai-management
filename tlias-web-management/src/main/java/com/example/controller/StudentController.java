package com.example.controller;

import com.example.anno.Log;
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
    @Log
    @PostMapping
      public Result save(@RequestBody Student student) {
        log.info("新增学生{}",student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 批量删除学生
     */
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除学生{}",ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 根据id查询学生
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询学生{}",id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * 修改学生信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学生{}",student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 违纪处理
     */
    @Log
    @PostMapping("/violation")
    public Result violation(@RequestBody Student student) {
        log.info("违纪处理{}",student);
        studentService.violation(student);
        return Result.success();
    }
}
