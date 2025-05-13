package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.Result;
import com.example.pojo.pageResult;
import com.example.servive.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工管理
 */
@RestController
@Slf4j
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService  empService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,@RequestParam (defaultValue = "10") Integer pageSize) {
        log.info("分页查询，参数：{}，{}", page, pageSize);
        pageResult<Emp> pageResult = empService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
