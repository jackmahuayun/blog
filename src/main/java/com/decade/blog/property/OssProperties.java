package com.decade.blog.property;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 在服务器启动时候,让这个类读取配置文件内容
 *
 * @author decade
 * @create 2019-12-21 16:50
 */
@Component
public class OssProperties implements InitializingBean {

    @Value("${aliyun.oss.file.endPoint}")
    private String endPoint;

    @Value("${aliyun.oss.file.keyId}")
    private String keyId;

    @Value("${aliyun.oss.file.keySecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;

    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;


    /**
     * 服务器启动时候OssPropertiesConst初始化
     * 调用里面afterPropertiesSet读取配置文件内容
     */
    @Override
    public void afterPropertiesSet() {
        END_POINT = endPoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
