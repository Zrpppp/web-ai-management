package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.Result;
import com.example.pojo.pageResult;
import com.example.servive.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理
 */
@RestController
@Slf4j
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询，参数：{}",empQueryParam);
        pageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 保存员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("保存员工，员工信息：{}",emp);
        empService.save(emp);
        return Result.success();
    }
    /**
     * 批量删除员工
     * public Result delete(@RequestParam Integer[] ids)
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除员工，员工id：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询员工信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询员工信息，员工id：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工信息，员工信息：{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
