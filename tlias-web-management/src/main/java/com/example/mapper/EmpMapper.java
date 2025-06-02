package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     */
    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long count();

    /**
     * 分页查询
     */
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") //获取到生成的主键 --- 主键返回
    @Insert("insert into emp(username,name,gender,image,job,dept_id,phone,salary,entry_date,create_time,update_time) " +
            "values(#{username},#{name},#{gender},#{image},#{job},#{deptId},#{phone},#{salary},#{entryDate},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 批量删除员工
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息和工作经历
     */
    Emp getById(Integer id);

    /**
     * 修改员工信息
     */
    void updateById(Emp emp);

    /**
     * 查询员工职位数量
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();
}
