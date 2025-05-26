package com.example.servive;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.pageResult;

import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     */
    pageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */
    void save(Emp emp);

    /**
     * 员工删除
     */
    void delete(List<Integer> ids);
}
