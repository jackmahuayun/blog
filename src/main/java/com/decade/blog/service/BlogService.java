package com.decade.blog.service;

import com.decade.blog.entity.Blog;
import com.decade.blog.entity.query.BlogQueryDTO;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;

/**
 * @author decade
 * @create 2019-12-21 09:56
 */
public interface BlogService {


    /**
     * 根据标签查询所属博客
     *
     * @param current
     * @param size
     * @param tagId
     * @return
     */
    PageInfo<Blog> findBlogByTag(Integer current, Integer size, String tagId);

    /**
     * 添加博客
     *
     * @param blog
     * @param session
     * @return
     */
    boolean save(Blog blog, HttpSession session);

    /**
     * 条件查询博客分页
     *
     * @param current
     * @param size
     * @param blogQueryDTO
     * @return
     */
    PageInfo<Blog> findAllBlog(Integer current, Integer size, BlogQueryDTO blogQueryDTO);
}
