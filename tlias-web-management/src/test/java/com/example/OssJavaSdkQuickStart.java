package com.example;

import java.io.*;
import java.nio.file.Files;
import java.util.Random;

import com.aliyun.oss.*;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import org.junit.jupiter.api.Test;

public class OssJavaSdkQuickStart {
    /** 生成一个唯一的 Bucket 名称 */
    public static String generateUniqueBucketName(String prefix) {
        // 获取当前时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());
        // 生成一个 0 到 9999 之间的随机数
        Random random = new Random();
        int randomNum = random.nextInt(10000); // 生成一个 0 到 9999 之间的随机数
        // 连接以形成一个唯一的 Bucket 名称
        return prefix + "-" + timestamp + "-" + randomNum;
    }
    @Test
    public void test01() throws Exception{
        // Endpoint以华东1（杭州）为例，填写为https://oss-cn-hangzhou.aliyuncs.com，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-guangzhou.aliyuncs.com";
        String bucketName = "java-ai-zrp";
        // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
        // 关于OSS支持的Region与Endpoint的对应关系，请参见https://www.alibabacloud.com/help/zh/oss/user-guide/regions-and-endpoints。
        String region = "cn-guangzhou";

        // 从环境变量中获取访问凭证。运行本代码示例之前，请先配置环境变量
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建OSSClient实例。
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        // 显式声明使用 V4 签名算法
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
        try {
//            // 1. 创建存储空间（Bucket）
//            ossClient.createBucket(bucketName);
//            System.out.println("1. Bucket " + bucketName + " 创建成功。");
            // 2. 上传文件
            String objectName = "zrp.jpg";
            File file = new File("G:\\code\\images\\eb575324-6129-4de7-b98b-0a7206b1226a-.jpg");
            byte[] content = Files.readAllBytes(file.toPath());
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
            System.out.println("2. 文件 " + objectName + " 上传成功。");
//            // 3. 下载文件
//            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
//            InputStream contentStream = ossObject.getObjectContent();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(contentStream));
//            String line;
//            System.out.println("3. 下载的文件内容：");
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            contentStream.close();
//            // 4. 列出文件
//            System.out.println("4. 列出 Bucket 中的文件：");
//            ObjectListing objectListing = ossClient.listObjects(bucketName);
//            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
//                System.out.println(" - " + objectSummary.getKey() + " (大小 = " + objectSummary.getSize() + ")");
//            }
//            // 5. 删除文件
//            ossClient.deleteObject(bucketName, objectName);
//            System.out.println("5. 文件 " + objectName + " 删除成功。");
//            // 6. 删除存储空间（Bucket）
//            ossClient.deleteBucket(bucketName);
//            System.out.println("6. Bucket " + bucketName + " 删除成功。");
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}