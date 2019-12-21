package com.decade.blog.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagMapperTest {

    @Resource
    private TagMapper tagMapper;

    @Test
    public void test01() {
        System.out.println(tagMapper.findOneTag("0"));
        System.out.println(tagMapper.findTwoTag("0"));
    }

}