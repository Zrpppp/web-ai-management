package com.example.controller;

import com.example.pojo.*;
import com.example.servive.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     *  分页查询
     */
    @GetMapping
     public Result list(ClazzQueryParam clazzQueryParam) {
        log.info("查询所有{}",clazzQueryParam);
        pageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     *  添加班级
     */
    @PostMapping
     public Result save(@RequestBody Clazz clazz) {
        log.info("添加班级{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     *  批量删除班级
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除班级{}",ids);
        clazzService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询班级
     */
     @GetMapping("/{id}")
     public Result getInfo(@PathVariable Integer id) {
          log.info("查询班级{}",id);
           Clazz clazz = clazzService.getInfo(id);
           return Result.success(clazz);
     }

    /**
     * 修改班级
     */
     @PutMapping
     public Result update(@RequestBody Clazz clazz) {
         log.info("修改班级{}",clazz);
         clazzService.update(clazz);
         return Result.success();
     }
}
