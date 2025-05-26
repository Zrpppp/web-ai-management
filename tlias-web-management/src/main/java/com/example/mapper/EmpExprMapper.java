package com.example.mapper;

import com.example.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工工作经历
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 批量删除员工工作经历
     */
    void deleteByEmpIds(List<Integer> ids);
}
