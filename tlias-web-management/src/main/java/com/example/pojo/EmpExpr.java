package com.example.pojo;

import lombok.Data;

/**
 * 工作经历
 */
@Data
public class EmpExpr {
    private Integer id;
    private Integer empId;
    private String beginDate;
    private String endDate;
    private String company;
    private String job;
}
