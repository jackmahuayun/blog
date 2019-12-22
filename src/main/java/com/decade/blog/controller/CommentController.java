package com.decade.blog.controller;

import com.decade.blog.entity.Comment;
import com.decade.blog.entity.User;
import com.decade.blog.enums.ResultEnum;
import com.decade.blog.service.CommentService;
import com.decade.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author decade
 * @create 2019-12-22 11:38
 */
@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/find/{blogId}")
    public Result findAllCommentByBlogId(@PathVariable("blogId") String blogId) {
        List<Comment> commentList = commentService.findAllCommentByBlogId(blogId);
        return Result.success(ResultEnum.DEFAULT_SUCCESS).set("commentList", commentList);
    }

    @PostMapping("save")
    public Result save(@RequestBody Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        comment.setNickname(user.getNickname());
        comment.setAvatar(user.getAvatar());
        boolean save = commentService.save(comment);
        if (save) {
            return Result.success(ResultEnum.DEFAULT_SUCCESS);
        }
        return Result.fail(ResultEnum.SAVE_FAIL);
    }

}
