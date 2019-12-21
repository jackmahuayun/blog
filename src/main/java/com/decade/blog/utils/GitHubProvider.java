package com.decade.blog.utils;

import com.alibaba.fastjson.JSON;
import com.decade.blog.entity.dto.AccessTokenDTO;
import com.decade.blog.entity.dto.UserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author decade
 * @create 2019-12-21 13:43
 */
@Component
public class GitHubProvider {

    /**
     * 获取AccessToken
     *
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据AccessToken获取用户对象
     *
     * @param accessToken
     * @return
     */
    public UserDTO getUserByAccessToken(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println("string = " + string);
            //将字符串转换为对象
            UserDTO userDTO = JSON.parseObject(string, UserDTO.class);
            return userDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
