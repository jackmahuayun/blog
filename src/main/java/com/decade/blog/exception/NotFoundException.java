package com.decade.blog.exception;

import com.decade.blog.enums.ResultEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author decade
 * @create 2019-11-24 09:27
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
    }

}
