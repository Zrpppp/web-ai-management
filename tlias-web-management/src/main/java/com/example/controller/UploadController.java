package com.example.controller;

import com.example.pojo.Result;
import com.example.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    /**
     * 本地磁盘存储
     */
    /*@PostMapping
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("文件上传:{},{},{}", name, age, file);
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + "-" + extension;
        file.transferTo(new File("G:/code/images/" + newFileName));
        return Result.success();
    }*/

    /**
     * 阿里云OSS存储
     */
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传:{}", file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }

}
