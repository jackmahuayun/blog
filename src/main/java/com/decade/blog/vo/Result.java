package com.decade.blog.vo;

import com.decade.blog.enums.ResultEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author decade
 * @create 2019-11-23 19:46
 */
@Data
public class Result {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 具体内容
     */
    private Map<String, Object> data = new HashMap<>();


    /**
     * 手动设置对象返回
     *
     * @param key
     * @param value
     * @return
     */
    public Result set(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    /**
     * 成功
     *
     * @return
     */
    public static Result success(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    /**
     * 失败
     *
     * @param message
     * @return
     */
    public static Result fail(String message) {
        Result result = new Result();
        result.setMessage(message);
        return result;
    }

    /**
     * 失败
     *
     * @param resultEnum
     * @return
     */
    public static Result fail(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

}
