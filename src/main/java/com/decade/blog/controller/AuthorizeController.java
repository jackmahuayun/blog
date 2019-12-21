package com.decade.blog.controller;

import com.decade.blog.entity.User;
import com.decade.blog.entity.dto.AccessTokenDTO;
import com.decade.blog.entity.dto.UserDTO;
import com.decade.blog.enums.ResultEnum;
import com.decade.blog.property.GitHubProperties;
import com.decade.blog.service.UserService;
import com.decade.blog.utils.GitHubProvider;
import com.decade.blog.utils.Snowflake;
import com.decade.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author decade
 * @create 2019-12-21 14:40
 */
@RestController
@CrossOrigin
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserService userService;

    /**
     * GitHub登录(qq登录 微信登录以后完善)
     * https://github.com/login/oauth/authorize?client_id=Iv1.c08e07bf8ea38199&redirect_uri=http://localhost:8001/callback&scope=user&state=1
     *
     * @param code
     * @param state
     * @param response
     * @return
     */
    @GetMapping("/callback")
    public Result callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletResponse response) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(GitHubProperties.CLIENT_ID);
        accessTokenDTO.setClient_secret(GitHubProperties.CLIENT_SECRET);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(GitHubProperties.REDIRECT_URI);
        accessTokenDTO.setState(state);

        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println("accessToken = " + accessToken);
        UserDTO userDTO = gitHubProvider.getUserByAccessToken(accessToken);

        if (userDTO != null) {
            //登录成功,并将登录成功后的数据存到数据库,同时将cookie写入浏览器端
            User user = new User();
            user.setAccountId(userDTO.getId().toString());
            user.setNickname(userDTO.getName());
            Snowflake snowflake = new Snowflake(1, 1, 1);
            String token = snowflake.nextId() + "";
            user.setToken(token);
            user.setAvatar(userDTO.getAvatarUrl());
            userService.register(user);
            response.addCookie(new Cookie("token", token));
            return Result.success(ResultEnum.DEFAULT_SUCCESS);
        } else {
            return Result.success(ResultEnum.DEFAULT_SUCCESS);
        }
    }
}
