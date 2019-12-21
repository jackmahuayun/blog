package com.decade.blog.controller;

import com.decade.blog.entity.User;
import com.decade.blog.enums.ResultEnum;
import com.decade.blog.service.UserService;

import com.decade.blog.vo.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author decade
 * @create 2019-12-21 10:35
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 普通账号登录
     *
     * @param nickname
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam("nickname") String nickname,
                        @RequestParam("password") String password,
                        HttpSession session) {
        User user = userService.login(nickname, password);
        if (user != null) {
            session.setAttribute("user", user);
            return Result.success(ResultEnum.DEFAULT_SUCCESS);
        }
        return Result.fail(ResultEnum.LOGIN_FAIL);
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Validated User user) {
        boolean register = userService.register(user);
        if (register) {
            return Result.success(ResultEnum.DEFAULT_SUCCESS);
        }
        return Result.fail(ResultEnum.REGISTER_FAIL);
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return Result.success(ResultEnum.DEFAULT_SUCCESS);
    }

    @GetMapping("/find/{current}/{size}")
    public Result findAllUser(@PathVariable("current") Integer current,
                              @PathVariable("size") Integer size) {
        PageInfo<User> pageInfo = userService.findAllUser(current, size);
        return Result.success(ResultEnum.DEFAULT_SUCCESS).set("page", pageInfo);
    }

}
