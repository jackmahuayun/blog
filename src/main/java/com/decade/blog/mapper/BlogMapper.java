package com.decade.blog.mapper;

import com.decade.blog.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 09:56
 */
public interface BlogMapper {

    /**
     * 查询所有博客
     *
     * @return
     */
    List<Blog> findAllBlog();

    /**
     * 根据标签查询所属博客
     *
     * @param tagId
     * @return
     */
    List<Blog> findBlogByTagId(@Param("tagId") String tagId);

    /**
     * 添加博客
     *
     * @param blog
     * @return
     */
    int save(Blog blog);

}
