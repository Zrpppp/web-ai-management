package com.example.servive;

import com.example.pojo.reportOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计员工职位人数
     */
    reportOption getEmpJobData();

    /**
     * 统计员工性别人数
     */
    List<Map<String, Object>> getEmpSexData();

    /**
     * 统计班级人数
     */
    reportOption getClazzCountData();
}
