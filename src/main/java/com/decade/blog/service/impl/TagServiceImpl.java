package com.decade.blog.service.impl;

import com.decade.blog.entity.Tag;
import com.decade.blog.entity.dto.TagDTO;
import com.decade.blog.enums.ResultEnum;
import com.decade.blog.exception.BlogException;
import com.decade.blog.mapper.TagMapper;
import com.decade.blog.property.ExcelProperties;
import com.decade.blog.service.TagService;
import com.decade.blog.utils.Snowflake;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 15:20
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<TagDTO> findTagByRelation() {

        List<TagDTO> tagOneDTOList = new ArrayList<>(12);

        List<Tag> oneTagList = tagMapper.findOneTag("0");
        List<Tag> twoTagList = tagMapper.findTwoTag("0");

        for (Tag oneTag : oneTagList) {
            TagDTO tagOneDTO = new TagDTO();
            BeanUtils.copyProperties(oneTag, tagOneDTO);

            List<TagDTO> tagTwoDTOList = new ArrayList<>(12);

            for (Tag twoTag : twoTagList) {
                if (twoTag.getParentId().equals(oneTag.getId())) {
                    TagDTO tagTwoDTO = new TagDTO();
                    BeanUtils.copyProperties(twoTag, tagTwoDTO);
                    tagTwoDTOList.add(tagTwoDTO);
                }
                tagOneDTO.setChildren(tagTwoDTOList);
            }
            tagOneDTOList.add(tagOneDTO);
        }
        return tagOneDTOList;
    }

    @Override
    public List<String> importTagByExcel(MultipartFile file) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if (ExcelProperties.SUFFIX03.endsWith(extension) || ExcelProperties.SUFFIX07.endsWith(extension)) {
            List<String> tips = new ArrayList<>(12);
            Snowflake snowflake = new Snowflake(1, 1, 1);
            InputStream inputStream = null;
            try {
                inputStream = file.getInputStream();
                Workbook workbook = null;
                if (ExcelProperties.SUFFIX03.endsWith(extension)) {
                    //03版
                    workbook = new HSSFWorkbook(inputStream);
                } else if (ExcelProperties.SUFFIX07.endsWith(extension)) {
                    //07版
                    workbook = new XSSFWorkbook(inputStream);
                }
                //3 根据workbook获取sheet getSheetAt(0)获取第一个sheet
                Sheet sheet = workbook.getSheetAt(0);
                //4 sheet获取行数 lastRowNum表示最后的行数
                //循环遍历获取行,从第二行(Excel默认索引也是从0开始)开始获取数据
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    //得到excel每一行
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        String message = "第" + i + "行数据为空";
                        tips.add(message);
                        continue;
                    }
                    //获取第一列
                    Cell cellOne = row.getCell(0);
                    if (cellOne == null) {
                        String message = "第一列第" + i + "个单元格数据为空";
                        tips.add(message);
                        continue;
                    }
                    //列不为空获取第一列值
                    //一级标签值(标签名字)
                    String tagNameOne = cellOne.getStringCellValue();

                    //添加一级标签
                    //因为excel里面有很多重复的一级标签
                    //在添加一级标签之前判断要添加的一级标签在数据库表是否存在,如果不存在添加
                    Tag tag = tagMapper.findOne(tagNameOne);
                    //一级标签的ID
                    String oneId = null;
                    if (tag == null) {
                        //如果不存在相同名称添加
                        tag = new Tag();
                        tag.setId(snowflake.nextId() + "");
                        tag.setTagName(tagNameOne);
                        tag.setParentId("0");
                        tag.setGmtCreate(new Date());
                        tag.setGmtModified(new Date());
                        tagMapper.insert(tag);
                        //获取一级标签的ID 方便二级标签使用
                        oneId = tag.getId();
                    } else {
                        oneId = tag.getId();
                    }
                    //获取第二列
                    Cell cellTwo = row.getCell(1);
                    if (cellTwo == null) {
                        String message = "第二列第" + i + "个单元格数据为空";
                        tips.add(message);
                        continue;
                    }
                    //标签名字
                    String tagNameTwo = cellTwo.getStringCellValue();
                    //判断二级标签是否存在
                    if (tagMapper.existTwoTag(tagNameTwo, oneId) == null) {
                        //如果不存在相同名称添加
                        tag = new Tag();
                        tag.setId(snowflake.nextId() + "");
                        tag.setTagName(tagNameTwo);
                        tag.setParentId(oneId);
                        tag.setGmtCreate(new Date());
                        tag.setGmtModified(new Date());
                        tagMapper.insert(tag);
                    }
                }
                //返回消息提示
                return tips;
            } catch (Exception e) {
                e.printStackTrace();
                throw new BlogException(ResultEnum.IMPORT_FAIL);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            throw new BlogException(ResultEnum.NOT_SUPPORT_FILE);
        }
    }

    @Override
    public boolean delete(String tagId) {
        List<Tag> tagList = tagMapper.findByParentId(tagId);
        if (tagList.size() > 0) {
            throw new BlogException(ResultEnum.DELETE_FAIL);
        }
        return tagMapper.delete(tagId) > 0;
    }

}
