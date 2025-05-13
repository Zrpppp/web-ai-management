package com.example.servive.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.pageResult;
import com.example.servive.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public pageResult<Emp> page(Integer page, Integer pageSize) {
        //调用mapper接口，查询总记录数
        Long total = empMapper.count();

        //调用mapper接口，查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start,pageSize);

        return new pageResult<Emp>(total, rows);
    }
}
