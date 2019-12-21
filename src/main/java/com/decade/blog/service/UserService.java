package com.decade.blog.service;

import com.decade.blog.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 10:43
 */
public interface UserService {

    /**
     * 登录
     *
     * @param nickname
     * @param password
     * @return
     */
    User login(String nickname, String password);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 获取所有用户信息并分页
     *
     * @param current
     * @param size
     * @return
     */
    PageInfo<User> findAllUser(Integer current, Integer size);

}
