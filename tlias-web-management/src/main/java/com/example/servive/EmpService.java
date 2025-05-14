package com.example.servive;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.pageResult;

public interface EmpService {
    /**
     * 分页查询
     */
    pageResult<Emp> page(EmpQueryParam empQueryParam);
}
