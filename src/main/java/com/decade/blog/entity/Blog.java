package com.decade.blog.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客
 * </p>
 *
 * @author decade
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Blog对象", description = "博客")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客ID")
    private String id;

    @ApiModelProperty(value = "博客名称")
    private String title;

    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "博客封面图片路径")
    private String cover;

    @ApiModelProperty(value = "博客状态 0未发布 1已发布")
    private Boolean status;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "是否可以评论：0可以 1不可以")
    private Boolean comment;

    @ApiModelProperty(value = "是否原创：0原创 1转载")
    private Boolean original;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "标签ID")
    private String tagId;

    @ApiModelProperty(value = "用户")
    private User user;

    @ApiModelProperty(value = "标签")
    private List<Tag> tags;


}
