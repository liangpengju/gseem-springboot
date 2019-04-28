package com.gseem.lesson03.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(description = "响应对象")
public class BaseResult<T> {
    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "成功";

    @ApiModelProperty(value = "响应码", name = "code", required = true, example = "" + SUCCESS_CODE)
    private int code;

    @ApiModelProperty(value = "响应消息", name = "msg", required = true, example = SUCCESS_MESSAGE)
    private String msg;

    @ApiModelProperty(value = "响应数据", name = "data")
    private T data;

    public BaseResult() {
    }

    public BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}



