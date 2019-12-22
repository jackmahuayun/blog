package com.decade.blog.entity.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author decade
 * @create 2019-12-22 09:17
 */
@Data
public class BlogQueryDTO {

    @ApiModelProperty(value = "博客ID")
    private String id;

    @ApiModelProperty(value = "博客名称")
    private String title;

    @ApiModelProperty(value = "博客状态 0未发布 1已发布")
    private Boolean status;

    @ApiModelProperty(value = "是否原创：0原创 1转载")
    private Boolean original;

}
