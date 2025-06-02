package com.example.pojo;

import lombok.Data;

import java.time.LocalDate;
@Data
public class StudentQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private String no;
    private Integer gender;
    private String phone;
    private String idCard;
    private Integer isCollege;
    private String address;
    private Integer degree;
    private LocalDate beginData; //毕业开始日期
    private LocalDate endData;  //毕业结束日期
    private Integer clazzId;
}
