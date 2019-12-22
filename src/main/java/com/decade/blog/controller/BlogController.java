package com.decade.blog.controller;

import com.decade.blog.entity.Blog;
import com.decade.blog.entity.query.BlogQueryDTO;
import com.decade.blog.enums.ResultEnum;
import com.decade.blog.service.BlogService;
import com.decade.blog.vo.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author decade
 * @create 2019-12-21 09:45
 */
@RestController
@RequestMapping("/blog")
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 使用RequestBody一定要记得使用post请求
     *
     * @param current
     * @param size
     * @param blogQueryDTO
     * @return
     */
    @PostMapping("/find/{current}/{size}")
    public Result findAllBlog(@PathVariable("current") Integer current,
                              @PathVariable("size") Integer size,
                              @RequestBody(required = false) BlogQueryDTO blogQueryDTO) {
        PageInfo<Blog> pageInfo = blogService.findAllBlog(current, size, blogQueryDTO);
        return Result.success(ResultEnum.DEFAULT_SUCCESS).set("page", pageInfo);
    }

    @GetMapping("find/{current}/{size}/{tagId}")
    public Result findBlogByTag(@PathVariable("current") Integer current,
                                @PathVariable("size") Integer size,
                                @PathVariable("tagId") String tagId) {
        PageInfo<Blog> pageInfo = blogService.findBlogByTag(current, size, tagId);
        return Result.success(ResultEnum.DEFAULT_SUCCESS).set("page", pageInfo);
    }

    @PostMapping("save")
    public Result save(@RequestBody Blog blog, HttpSession session) {
        boolean save = blogService.save(blog, session);
        if (save) {
            return Result.success(ResultEnum.DEFAULT_SUCCESS);
        }
        return Result.fail(ResultEnum.SAVE_FAIL);
    }

}
