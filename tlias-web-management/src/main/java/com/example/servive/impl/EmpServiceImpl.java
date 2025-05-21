package com.example.servive.impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpExpr;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.pageResult;
import com.example.servive.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Override
    public pageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //解析查询结果并封装
        Page<Emp> p = (Page<Emp>) empList;

        return new pageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Emp emp) {
        //保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //保存员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            //为每个工作经历设置员工id
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }
}
