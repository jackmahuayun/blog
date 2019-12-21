package com.decade.blog.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author decade
 * @create 2019-12-21 11:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test01(){
        System.out.println(userMapper.login("admin", "202cb962ac59075b964b07152d234b70"));
    }

}