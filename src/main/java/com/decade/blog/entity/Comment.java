package com.decade.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author decade
 * @since 2019-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Comment对象", description = "评论")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论ID")
    private String id;

    @ApiModelProperty(value = "昵称")
    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "博客ID")
    private String blogId;

    private Blog blog;

    private List<Comment> comments;


}
