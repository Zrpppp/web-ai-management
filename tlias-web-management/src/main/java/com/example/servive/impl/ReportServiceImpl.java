package com.example.servive.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.jobOption;
import com.example.servive.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    /**
     * 统计员工职位人数
     */
    @Override
    public jobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();

        List<Object> jobList = list.stream().map(map -> map.get("pos")).toList();
        List<Object> dataList = list.stream().map(map -> map.get("num")).toList();

        return new jobOption(jobList,dataList);
    }

    /**
     * 统计员工性别人数
     */
    @Override
    public List<Map<String, Object>> getEmpSexData() {
        return empMapper.countEmpGenderData();
    }
}
