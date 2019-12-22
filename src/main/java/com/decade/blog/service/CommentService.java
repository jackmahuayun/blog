package com.decade.blog.service;

import com.decade.blog.entity.Comment;

import java.util.List;

/**
 * @author decade
 * @create 2019-12-22 11:40
 */
public interface CommentService {

    /**
     * 根据博客ID查询下面所有评论
     *
     * @param blogId
     * @return
     */
    List<Comment> findAllCommentByBlogId(String blogId);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    boolean save(Comment comment);
}
