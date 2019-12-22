package com.decade.blog.mapper;

import com.decade.blog.entity.Blog;
import com.decade.blog.entity.query.BlogQueryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author decade
 * @create 2019-12-22 09:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogMapperTest {

    @Resource
    private BlogMapper blogMapper;

    @Test
    public void test01() {
        BlogQueryDTO blogQueryDTO = new BlogQueryDTO();
        blogQueryDTO.setTitle("Java");
        List<Blog> blogList = blogMapper.findAllBlogByCondition(blogQueryDTO);
        System.out.println("blogList = " + blogList);
    }

}