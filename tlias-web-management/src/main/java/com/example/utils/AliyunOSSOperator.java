package com.example.utils;


import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliyunOSSOperator {
    //方式一，单个属性注入
    /*@Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.region}")
    private String region;*/

    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    public String upload(byte[] content,String originalFileName) throws Exception{

        String endpoint = aliyunOSSProperties.getEndpoint();
        String bucketName = aliyunOSSProperties.getBucketName();
        String region = aliyunOSSProperties.getRegion();



        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        //获取当前系统时间
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));

        //生成一个不重复的文件名
        String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        //创建一个OSSClient实例
        ClientBuilderConfiguration  clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);

        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            ossClient.putObject(bucketName,objectName,new ByteArrayInputStream(content));
        }finally {
            ossClient.shutdown();
        }

        return endpoint.split("//")[0] + "//" + bucketName + '.' + endpoint.split("//")[1] + '/' +  objectName;
    }

}
