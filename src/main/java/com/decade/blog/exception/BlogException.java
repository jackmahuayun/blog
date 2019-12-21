package com.decade.blog.exception;

import com.decade.blog.enums.ResultEnum;
import lombok.Data;

/**
 * NoArgsConstructor:无参构造
 * AllArgsConstructor:有参构造
 *
 * @author decade
 * @create 2019-11-24 09:18
 */
@Data
public class BlogException extends RuntimeException {

    private Integer code;

    public BlogException() {
    }

    public BlogException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public BlogException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
