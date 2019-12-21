package com.decade.blog.mapper;

import com.decade.blog.entity.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 15:21
 */
public interface TagMapper {

    /**
     * 添加标签
     *
     * @param tag
     * @return
     */
    int insert(Tag tag);

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @Delete("delete from tag where id = #{id}")
    int delete(@Param("id") String id);

    /**
     * 根据parentId查询
     *
     * @param parentId
     * @return
     */
    List<Tag> findByParentId(@Param("parentId") String parentId);

    /**
     * 根据标签名查询
     *
     * @param tagName
     * @return
     */
    @Select("select id,tag_name tagName,parent_id parentId from tag where tag_name = #{tagName} ")
    Tag findOne(@Param("tagName") String tagName);

    /**
     * 查询一级标签
     *
     * @param parentId
     * @return
     */
    List<Tag> findOneTag(@Param("parentId") String parentId);

    /**
     * 查询二级标签
     *
     * @param parentId
     * @return
     */
    List<Tag> findTwoTag(@Param("parentId") String parentId);

    /**
     * 判断一级标签是否存在
     *
     * @param tagName
     * @param parentId
     * @return
     */
    Tag existOneTag(@Param("tagName") String tagName, @Param("parentId") String parentId);

    /**
     * 判断二级标签是否存在
     *
     * @param tagName
     * @param parentId
     * @return
     */
    Tag existTwoTag(@Param("tagName") String tagName, @Param("parentId") String parentId);

}
