package com.decade.blog.property;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author decade
 * @create 2019-12-21 13:42
 */
@Component
public class GitHubProperties implements InitializingBean {

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect_uri}")
    private String redirect_uri;

    public static String CLIENT_ID;
    public static String CLIENT_SECRET;
    public static String REDIRECT_URI;


    @Override
    public void afterPropertiesSet() throws Exception {
        CLIENT_ID = client_id;
        CLIENT_SECRET = client_secret;
        REDIRECT_URI = redirect_uri;
    }
}
