package com.decade.blog.service.impl;

import com.decade.blog.entity.Comment;
import com.decade.blog.mapper.CommentMapper;
import com.decade.blog.service.CommentService;
import com.decade.blog.utils.Snowflake;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author decade
 * @create 2019-12-22 11:40
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findAllCommentByBlogId(String blogId) {
        List<Comment> commentList = commentMapper.findAllCommentByBlogId(blogId);
        return eachComment(commentList);
    }

    @Override
    public boolean save(Comment comment) {
        //根据parent_id判断是评论还是回复
        String parentId = comment.getParentId();
        Snowflake snowflake = new Snowflake(1, 1, 1);
        if ("0".equals(parentId)) {
            //保存
            comment.setId(snowflake.nextId() + "");
            comment.setGmtCreate(new Date());
            comment.setGmtModified(new Date());
        } else {
            //回复
            comment.setId(snowflake.nextId() + "");
            comment.setGmtCreate(new Date());
            comment.setGmtModified(new Date());
            comment.setParentId(parentId);
        }
        return commentMapper.save(comment) > 0;
    }

    /**
     * 循环每个顶级的评论节点
     *
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replies = comment.getComments();
            for (Comment reply : replies) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setComments(tempReplies);
            //清除临时存放区
            tempReplies = new ArrayList<>();
        }
    }

    /**
     * 存放迭代找出的所有子代的集合
     */
    private List<Comment> tempReplies = new ArrayList<>();

    /**
     * 递归迭代,剥洋葱
     *
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        //顶节点添加到临时存放集合
        tempReplies.add(comment);
        if (comment.getComments().size() > 0) {
            List<Comment> replies = comment.getComments();
            for (Comment reply : replies) {
                tempReplies.add(reply);
                if (reply.getComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }

}
