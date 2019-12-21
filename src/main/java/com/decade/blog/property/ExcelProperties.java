package com.decade.blog.property;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author decade
 * @create 2019-11-26 12:48
 */
@Component
public class ExcelProperties implements InitializingBean {

    @Value("${excel.suffix03}")
    private String suffix03;

    @Value("${excel.suffix07}")
    private String suffix07;

    public static String SUFFIX03;

    public static String SUFFIX07;

    @Override
    public void afterPropertiesSet() {
        SUFFIX03 = suffix03;
        SUFFIX07 = suffix07;
    }
}
