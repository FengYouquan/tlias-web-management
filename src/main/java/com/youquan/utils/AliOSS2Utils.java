package com.youquan.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.youquan.config.AliYunOSSConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component
public class AliOSS2Utils {
    // 方式一：使用@Value(${})方式获取配置文件中的值
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    // @Value("${aliyun.oss.endpoint}")
    // private String endpoint;

    // STS临时访问密钥AccessKey ID和AccessKey Secret
    // @Value("${aliyun.oss.accessKeyId}")
    // private String accessKeyId;
    // @Value("${aliyun.oss.accessKeySecret}")
    // private String accessKeySecret;

    // @Value("${aliyun.oss.securityToken}")
    // STS安全令牌SecurityToken。
    // private String securityToken;

    // @Value("${aliyun.oss.bucketName}")
    // 填写Bucket名称，例如examplebucket。
    // private String bucketName;

    // 测试接收数组或集合数据
    // @Value("${aliyun.oss.program}")
    // private String[] hobbies;

    // 方式二：将阿里云OSS的相关配置参数封装成Bean对象
    private final AliYunOSSConfig aliYunOSSConfig;

    public AliOSS2Utils(AliYunOSSConfig aliYunOSSConfig) {
        this.aliYunOSSConfig = aliYunOSSConfig;
    }

    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException {
        // 方式二：获取阿里云OSS的相关参数
        String endpoint = aliYunOSSConfig.getEndpoint();
        String accessKeyId = aliYunOSSConfig.getAccessKeyId();
        String accessKeySecret = aliYunOSSConfig.getAccessKeySecret();
        String securityToken = aliYunOSSConfig.getSecurityToken();
        String bucketName = aliYunOSSConfig.getBucketName();

        // 使用代码嵌入的STS临时访问密钥和安全令牌配置访问凭证
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret, securityToken);

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf("."));

        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt
        String objectName = "TliasDir/".concat(fileName);

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            // 获取上传的文件的输入流
            InputStream inputStream = file.getInputStream();
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 创建PutObject请求
            PutObjectResult result = ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            // 关闭ossClient
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        // 文件访问路径
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;// 把上传到oss的路径返回
    }

}
