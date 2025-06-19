package com.example.servive.impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.*;
import com.example.servive.EmpLogService;
import com.example.servive.EmpService;
import com.example.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmpServiceImpl implements EmpService {

    /**
     * 文件上传
     */
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public pageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //解析查询结果并封装
        Page<Emp> p = (Page<Emp>) empList;

        return new pageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}) // 事务控制 --- 默认出现运行时异常RunTimeException才会回滚
    public void save(Emp emp) {
        try {
            //保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            //保存员工工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                //为每个工作经历设置员工id
                exprList.forEach(expr -> expr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "添加员工信息:" + emp);
            empLogService.insertLog(empLog);
        }
    }

    /**
     * 批量删除员工
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //删除员工基本信息
        empMapper.deleteByIds(ids);
        //删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    /**
     * 根据id获取员工信息
     */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //根据id修改员工工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        List<EmpExpr> exprList = emp.getExprList();

        if (!CollectionUtils.isEmpty(exprList)) {
            //为每个工作经历设置员工id
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    /**
     * 登录
     */
    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        if (e != null){

            //生成 token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String token = JwtUtils.generateJWT(claims);
            
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(),token);
        }
        return null;
    }
}
