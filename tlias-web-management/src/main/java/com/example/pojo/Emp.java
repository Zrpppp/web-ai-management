package com.example.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工
 */
@Data
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String image;
    private String job;
    private Integer deptId;
    private String phone;
    private String salary;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDate entryDate;

    //封装部门名称
    private String deptName;
    //封装工作经历
    private List<EmpExpr> exprList;
}
