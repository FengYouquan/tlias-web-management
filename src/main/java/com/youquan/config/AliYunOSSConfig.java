package com.youquan.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliYunOSSConfig {
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    private String endpoint;

    // STS临时访问密钥AccessKey ID和AccessKey Secret
    private String accessKeyId;
    private String accessKeySecret;

    // STS安全令牌SecurityToken。
    private String securityToken;

    // 填写Bucket名称，例如examplebucket。
    private String bucketName;
}
