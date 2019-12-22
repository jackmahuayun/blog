package com.decade.blog.mapper;

import com.decade.blog.entity.Blog;
import com.decade.blog.entity.query.BlogQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 09:56
 */
public interface BlogMapper {

    /**
     * 条件查询所有博客
     *
     * @param blogQueryDTO
     * @return
     */
    List<Blog> findAllBlogByCondition(BlogQueryDTO blogQueryDTO);

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
