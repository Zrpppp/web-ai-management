package com.example.controller;

import com.example.pojo.*;
import com.example.servive.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
        log.info("查询所有");
        pageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     *  添加班级
     */
    @PostMapping
     public Result save(@RequestBody Clazz clazz) {
        log.info("添加班级");
        clazzService.save(clazz);
        return Result.success();
    }
}
