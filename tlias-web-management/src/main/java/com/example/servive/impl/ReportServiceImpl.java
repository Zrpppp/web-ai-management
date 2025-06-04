package com.example.servive.impl;

import com.example.mapper.ClazzMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.reportOption;
import com.example.servive.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private ClazzMapper clazzMapper;
    /**
     * 统计员工职位人数
     */
    @Override
    public reportOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();

        List<Object> jobList = list.stream().map(map -> map.get("pos")).toList();
        List<Object> dataList = list.stream().map(map -> map.get("num")).toList();

        return new reportOption(jobList,dataList);
    }

    /**
     * 统计员工性别人数
     */
    @Override
    public List<Map<String, Object>> getEmpSexData() {
        return empMapper.countEmpGenderData();
    }

    /**
     * 统计员工班级人数
     */
    @Override
    public reportOption getClazzCountData() {
        List<Map<String,Object>> list = clazzMapper.countClazzData();

        List<Object> clazzList = list.stream().map(map -> map.get("clazzName")).toList();
        List<Object> dataList = list.stream().map(map -> map.get("clazzCount")).toList();

        return new reportOption(clazzList,dataList);
    }
}
