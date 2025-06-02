package com.example.servive.impl;

import com.example.mapper.ClazzMapper;
import com.example.pojo.*;
import com.example.servive.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 分页查询
     */
    @Override
    public pageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        //解析查询结果并封装
        Page<Clazz> p = (Page<Clazz>) clazzList;

        return new pageResult<Clazz>(p.getTotal(), p.getResult());
    }

    /**
     * 保存班级
     */
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        clazzMapper.deleteByIds(ids);
    }

    /**
     * 根据id 查询班级信息
     */
    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getById(id);
    }

    /**
     * 修改班级信息
     */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    /**
     *  查询所有班级信息
     */
    @Override
    public List<Clazz> list() {
        return clazzMapper.findAll();
    }
}
