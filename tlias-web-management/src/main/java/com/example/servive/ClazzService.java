package com.example.servive;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import com.example.pojo.pageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClazzService {
    /**
     * 分页查询
     */
    pageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 保存
     * @param clazz
     */
    void save(Clazz clazz);

    /**
     * 批量删除
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询
     */
    Clazz getInfo(Integer id);

    /**
     * 修改
     */
    void update(Clazz clazz);

    /**
     * 查询所有班级
     */
    List<Clazz> list();
}
