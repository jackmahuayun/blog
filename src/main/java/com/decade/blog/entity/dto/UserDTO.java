package com.decade.blog.entity.dto;

import lombok.Data;

/**
 * @author decade
 * @create 2019-12-21 13:45
 */
@Data
public class UserDTO {

    /**
     * 填写的用户名
     */
    private String name;

    /**
     * 唯一生成的ID
     */
    private Long id;

    /**
     * 填写的bio信息
     */
    private String bio;

    /**
     * 用户头像
     */
    private String avatarUrl;

}
