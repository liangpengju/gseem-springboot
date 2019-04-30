package com.gseem.lesson04.exception;

import com.gseem.lesson04.util.ResponseCodeEnum;
import lombok.Getter;

/**
 * @author liangpengju-ds
 */
@Getter
public class DBException extends RuntimeException {

    private String code;
    private String message;

    public DBException(String message) {
        this.message = message;
    }

    public DBException(ResponseCodeEnum responseCodeEnum){
        this.code = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getMessage();
    }

    public DBException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
