package com.decade.blog.service;

import com.decade.blog.entity.dto.TagDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 15:20
 */
public interface TagService {

    /**
     * 维护一二级标签的关系
     *
     * @return
     */
    List<TagDTO> findTagByRelation();

    /**
     * 导入标签到数据库
     *
     * @param file
     * @return
     */
    List<String> importTagByExcel(MultipartFile file);

    /**
     * 删除标签
     *
     * @param tagId
     * @return
     */
    boolean delete(String tagId);


}
