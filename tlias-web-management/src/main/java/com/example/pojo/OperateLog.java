package com.example.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperateLog {
    private Integer id;
    private Integer operateEmpId; //操作员id
    private LocalDateTime operateTime; //操作时间
    private String className; //类名
    private String methodName; //方法名
    private String methodParams; //方法参数
    private String returnValue; //返回值
    private Long costTime;  //执行时长
}
