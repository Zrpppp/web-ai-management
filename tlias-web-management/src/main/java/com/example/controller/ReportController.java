package com.example.controller;

import com.example.pojo.Result;
import com.example.pojo.reportOption;
import com.example.servive.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    /**
     * 统计员工职位人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData()
    {
        log.info("统计员工职位人数");
        reportOption reportOption = reportService.getEmpJobData();
        return Result.success(reportOption);
    }

    /**
     * 统计员工性别人数
     */
    @GetMapping("/empSexData")
    public Result getEmpSexData()
    {
        log.info("统计员工性别人数");
        List<Map<String,Object>> genderList = reportService.getEmpSexData();
        return Result.success(genderList);
    }

    /**
     * 班级人数统计
     */
    @GetMapping("/clazzCountData")
    public Result getClazzCountData()
    {
        log.info("班级人数统计");
        reportOption clazzCountList = reportService.getClazzCountData();
        return Result.success(clazzCountList);
    }
}
