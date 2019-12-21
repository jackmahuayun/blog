package com.decade.blog.enums;

import lombok.Getter;

/**
 * @author decade
 * @create 2019-11-23 19:49
 */
@Getter
public enum ResultEnum {

    DEFAULT_SUCCESS(20000, "操作成功"),
    GLOBAL_ERROR(50000, "服务器走丢了,请联系管理员"),
    FIND_FAIL(1001, "查询失败"),
    SAVE_FAIL(1002, "添加失败"),
    UPDATE_FAIL(1003, "更新失败"),
    DELETE_FAIL(1004, "删除失败"),
    UPLOAD_FAIL(1005, "上传失败"),
    IMPORT_FAIL(1006, "导入数据不完整"),
    NOT_SUPPORT_FILE(1007, "不支持的文件类型"),
    LOGIN_FAIL(1008, "用户名或密码错误"),
    REGISTER_FAIL(1009, "注册失败");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
