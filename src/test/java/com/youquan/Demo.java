package com.youquan;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.FileInputStream;
import java.io.InputStream;

public class Demo {

    public static void main(String[] args) throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
        // 方式一
        // 强烈建议不要把访问凭证保存到工程代码里，否则可能导致访问凭证泄露，威胁您账号下所有资源的安全。本代码示例以从环境变量中获取访问凭证为例。运行本代码示例之前，请先配置环境变量。
        // EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 方式二
        // STS临时访问密钥AccessKey ID和AccessKey Secret。
        String accessKeyId = "STS.NT99NJVVWiyEjUC3sYkcBjMBR";
        String accessKeySecret = "65pE3yMpygxhi1JEAr7sjV7nEAane2bj5xQRKK36p6wg";
        // STS安全令牌SecurityToken。
        String securityToken = "CAISnwJ1q6Ft5B2yfSjIr5eMcvT+u4l23ruuaHPy13MMZ+xuhYjpsDz2IHtOfHBgAOsZsf41lWBZ7/oalqJ9RplKVEvZdNZ56MzRV9cuntOT1fau5Jko1be0ewHKeQKZsebWZ+LmNpy/Ht6md1HDkAJq3LL+bk/Mdle5MJqP+/kFC9MMRVuAcCZhDtVbLRcYgq18D3bKMuu3ORPHm3fZCFES2jBxkmRi86+ysIP+phPVlw/90fRH5dazcJapacZ9O4pmTt6zm8Fmf6/Z1CN86gINtoUO1fcdpGuX4Y7NWgIBskzWbdC5qIM/cFVLAYEhALNBofTGkvl1h/fejYyfyWwWZrsNCnSGH9//mpGVSLLwao4jG6zyPnPWycBl2VtyBubnCxqAAVIKYlNL9wURi1MCmIAXL+wyCCc7lHLkDU357Z94Z5y8LOeK1e1J35M7vhuxOLAbZGrhiF0XsYq0F8X0p9YCnKACZApnIATlcUHaMfjhVf9zvfuM4Uc8xzJhmsdEg+3H/WSCUOvIcZNk8ZDJG5gapL09XVLsf11JyGOR65l/fSDb";
        // 使用代码嵌入的STS临时访问密钥和安全令牌配置访问凭证。
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret, securityToken);

        // 填写Bucket名称，例如examplebucket。
        String bucketName = "fengyouquan";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "ExampleDir/th.jpg";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        String filePath = "D:\\Document\\Temporary\\th.jpg";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            InputStream inputStream = new FileInputStream(filePath);
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 创建PutObject请求。
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
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
} 