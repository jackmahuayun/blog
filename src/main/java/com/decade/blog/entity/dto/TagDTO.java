package com.decade.blog.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 15:16
 */
@Data
public class TagDTO {

    @ApiModelProperty(value = "博客ID")
    private String id;

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @ApiModelProperty(value = "子标签")
    private List<TagDTO> children;


}
