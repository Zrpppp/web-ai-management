package com.example.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private String room;
    private Integer masterId;
    private Integer subject;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate time;
}
