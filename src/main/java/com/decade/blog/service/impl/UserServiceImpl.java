package com.decade.blog.service.impl;

import com.decade.blog.entity.User;
import com.decade.blog.enums.ResultEnum;
import com.decade.blog.exception.LoginException;
import com.decade.blog.mapper.UserMapper;
import com.decade.blog.service.UserService;
import com.decade.blog.utils.MD5Util;
import com.decade.blog.utils.Snowflake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 10:43
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String nickname, String password) {
        //加密
        String encryption = MD5Util.digest(password);
        System.out.println("encryption = " + encryption);
        User user = userMapper.login(nickname, encryption);
        if (user == null) {
            throw new LoginException(ResultEnum.LOGIN_FAIL);
        }
        if (!encryption.equals(user.getPassword())) {
            throw new LoginException(ResultEnum.LOGIN_FAIL);
        }
        return user;
    }

    @Override
    public boolean register(User user) {
        Snowflake snowflake = new Snowflake(1, 1, 1);
        long id = snowflake.nextId();
        user.setId(id + "");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        return userMapper.register(user);
    }

    @Override
    public PageInfo<User> findAllUser(Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<User> userList = userMapper.findAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

}
