package com.decade.blog.mapper;

import com.decade.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author decade
 * @create 2019-12-22 11:42
 */
public interface CommentMapper {

    /**
     * 根据博客ID查询下面所有一级评论
     *
     * @param blogId
     * @return
     */
    List<Comment> findAllCommentByBlogId(@Param("blogId") String blogId);

    /**
     * 保存评论
     *
     * @param comment
     * @return
     */
    Integer save(Comment comment);
}
