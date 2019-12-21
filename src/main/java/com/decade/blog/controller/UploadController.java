package com.decade.blog.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.decade.blog.enums.ResultEnum;
import com.decade.blog.property.OssProperties;
import com.decade.blog.vo.Result;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author decade
 * @create 2019-12-21 16:23
 */
@RestController
@RequestMapping("/oss")
@CrossOrigin
public class UploadController {

    @PostMapping("upload")
    public Result uploadAvatar(@RequestParam("avatar") MultipartFile avatar,
                               @RequestParam(value = "dir", required = false) String dir) {
        String endPoint = OssProperties.END_POINT;
        String accessKeyId = OssProperties.KEY_ID;
        String accessKeySecret = OssProperties.KEY_SECRET;
        String bucketName = OssProperties.BUCKET_NAME;

        OSS ossClient = null;
        InputStream inputStream = null;

        try {
            inputStream = avatar.getInputStream();
            //格式化时间
            String filePath = new DateTime().toString("yyyy/MM/dd");
            String filename = filePath + "/" + UUID.randomUUID().toString() + ".jpg";

            //区分用户头像和博客封面头像
            if (!StringUtils.isEmpty(dir)) {
                filename = filePath + "/" + dir + "/" + UUID.randomUUID().toString() + ".jpg";
            }

            //创建OSSClient实例
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, filename, inputStream);

            String path = "https://" + bucketName + "." + endPoint + "/" + filename;
            return Result.success(ResultEnum.DEFAULT_SUCCESS).set("imgUrl", path);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultEnum.UPLOAD_FAIL);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ossClient != null) {
                //关闭OSSClient
                ossClient.shutdown();
            }
        }
    }
}
