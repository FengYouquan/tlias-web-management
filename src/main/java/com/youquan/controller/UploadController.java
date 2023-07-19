package com.youquan.controller;

import com.youquan.common.Result;
import com.youquan.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Fengyouquan
 */
@Slf4j
@RestController
public class UploadController {
    private final AliOSSUtils aliOSSUtils;

    public UploadController(AliOSSUtils aliOSSUtils) {
        this.aliOSSUtils = aliOSSUtils;
    }

    /**
     * 文件上传到阿里云OSS
     *
     * @param image 头像文件
     * @return 头像文件的URL
     * @throws IOException IO异常
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile image) throws IOException {
        log.info("文件上传,image:{}", image);
        String url = aliOSSUtils.upload(image);
        return Result.success(url);
    }
}
