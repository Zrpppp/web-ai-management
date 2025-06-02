package com.example.servive;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import com.example.pojo.pageResult;
import org.springframework.stereotype.Service;

@Service
public interface ClazzService {
    /**
     * 分页查询
     */
    pageResult<Clazz> page(ClazzQueryParam clazzQueryParam);
}
