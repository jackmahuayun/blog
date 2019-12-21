package com.decade.blog.service.impl;

import com.decade.blog.entity.Blog;
import com.decade.blog.entity.User;
import com.decade.blog.mapper.BlogMapper;
import com.decade.blog.service.BlogService;
import com.decade.blog.utils.Snowflake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 09:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public PageInfo<Blog> findAllBlog(Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<Blog> blogList = blogMapper.findAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> findBlogByTag(Integer current, Integer size, String tagId) {
        PageHelper.startPage(current, size);
        List<Blog> blogList = blogMapper.findBlogByTagId(tagId);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        return pageInfo;
    }

    @Override
    public boolean save(Blog blog, HttpSession session) {
        Snowflake snowflake = new Snowflake(1, 1, 1);
        blog.setId(snowflake.nextId() + "");
        blog.setGmtCreate(new Date());
        blog.setGmtModified(new Date());
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getId());
        return blogMapper.save(blog) > 0;
    }

}
