package com.decade.blog.controller;

import com.decade.blog.entity.Blog;
import com.decade.blog.entity.dto.TagDTO;
import com.decade.blog.enums.ResultEnum;
import com.decade.blog.service.TagService;
import com.decade.blog.vo.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 15:19
 */
@RestController
@RequestMapping("/tag")
@CrossOrigin
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/find")
    public Result findAllTagByRelation() {
        List<TagDTO> tagDTOList = tagService.findTagByRelation();
        return Result.success(ResultEnum.DEFAULT_SUCCESS).set("tagList", tagDTOList);
    }

    @PostMapping("/import")
    public Result importTagByExcel(@RequestParam("file") MultipartFile file) {
        List<String> tips = tagService.importTagByExcel(file);
        if (!tips.isEmpty()) {
            return Result.success(ResultEnum.DEFAULT_SUCCESS).set("tips", tips);
        }
        return Result.success(ResultEnum.DEFAULT_SUCCESS);
    }

    @DeleteMapping("/delete/{tagId}")
    public Result delete(@PathVariable("tagId") String tagId) {
        boolean delete = tagService.delete(tagId);
        if (delete) {
            return Result.success(ResultEnum.DEFAULT_SUCCESS);
        }
        return Result.fail(ResultEnum.DELETE_FAIL);
    }


}
