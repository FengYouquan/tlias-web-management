package com.youquan.config;

import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fengyouquan
 */
@Configuration
public class CommonConfig {

    /**
     * saxReader方法用于创建并返回一个SAXReader对象。
     *
     * @return SAXReader对象，用于解析XML文档。
     */
    @Bean
    public SAXReader saxReader() {
        return new SAXReader();
    }
}
