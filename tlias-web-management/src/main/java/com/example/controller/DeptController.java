package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.servive.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/dept")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门数据
     */

    @GetMapping
    public Result list() {
        log.info("查询所有部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门
     */
    @Log
    @DeleteMapping
    public Result delete(Integer id) {
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    /**
     * 更新部门
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        deptService.update(dept);
        return Result.success();
    }
}
