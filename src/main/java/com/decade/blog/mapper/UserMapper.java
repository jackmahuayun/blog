package com.decade.blog.mapper;

import com.decade.blog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 10:40
 */
public interface UserMapper {

    /**
     * 登录
     *
     * @param nickname
     * @param password
     * @return
     */
    User login(@Param("nickname") String nickname, @Param("password") String password);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 获取所有用户信息
     *
     * @return
     */
    List<User> findAllUser();
}
