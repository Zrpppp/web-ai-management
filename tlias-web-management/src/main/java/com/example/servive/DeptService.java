package com.example.servive;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门信息
     */
    List<Dept> findAll();

    /**
     * 删除部门
     */
    void deleteById(Integer id);

    /**
     * 新增部门
     */
    void add(Dept dept);

    /**
     * 根据id查询部门
     */
    Dept findById(Integer id);

    /**
     * 修改部门
     */
    void update(Dept dept);
}
