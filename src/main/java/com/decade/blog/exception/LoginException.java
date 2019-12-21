package com.decade.blog.exception;

import com.decade.blog.enums.ResultEnum;
import lombok.Data;

/**
 * @author decade
 * @create 2019-12-21 11:01
 */
@Data
public class LoginException extends RuntimeException {

    private Integer code;

    public LoginException() {}

    public LoginException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
