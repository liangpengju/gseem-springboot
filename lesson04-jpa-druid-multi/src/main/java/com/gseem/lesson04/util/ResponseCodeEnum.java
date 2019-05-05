package com.gseem.lesson04.util;

import lombok.Getter;

/**
 * 统一响应消息
 * @author liangpengju-ds
 */
@Getter
public enum ResponseCodeEnum {
    /**成功 1*/
    SUCCESS(Constant.RESULT_CODE_SUCCESS, "成功"),
    /**失败 0*/
    FAILURE(Constant.RESULT_CODE_FAILURE,"失败"),
    /**警告 2*/
    WARNING(Constant.RESULT_CODE_WARNING,"警告"),
    /**异常 -1*/
    ERROR(Constant.RESULT_CODE_ERROR,"异常"),
    /**参数错误*/
    ILLEGAL_ARGUMENT("-1", "参数错误"),
    /**非法操作*/
    ILLEGAL_OPERATE("-2", "非法操作"),
    /**系统错误*/
    INTERNAL_SERVER_ERROR("-3", "系统错误");

    /**code*/
    private String code;
    /**message*/
    private String message;

    /**
     * 统一封装
     * @param code
     * @param message
     */
    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
