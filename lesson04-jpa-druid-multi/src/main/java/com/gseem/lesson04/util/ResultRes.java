package com.gseem.lesson04.util;

import java.io.Serializable;

/**
 *
 */
public class ResultRes<T> implements Serializable{
    
    private static final long serialVersionUID = 1L;
    /**
     * 应答码
     */
    private String code;
    /**
     * 应答消息
     */
    private String msg;
    /**
     * 应答数据
     */
    private T data;

    public ResultRes(){}

    private ResultRes(String code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getcode() {
        return code;
    }

    public String getmsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ResultResBuilder<T> builder(){
        return new ResultResBuilder<>();
    }

    public static <T> ResultRes<T> ok(String msg, T data){
        return ResultRes.<T>builder()
                .code(Constant.RESULT_CODE_SUCCESS)
                .msg(msg)
                .data(data)
                .build();
    }

    public static <T> ResultRes<T> ok(String msg){
        return ResultRes.<T>builder()
                .code(Constant.RESULT_CODE_SUCCESS)
                .msg(msg)
                .build();
    }

    public static <T> ResultRes<T> ok(T data){
        return ResultRes.<T>builder()
                .code(Constant.RESULT_CODE_SUCCESS)
                .data(data)
                .build();
    }

    public static ResultRes fail(String msg){
        return ResultRes.builder()
                .code(Constant.RESULT_CODE_FAILURE)
                .msg(msg)
                .build();
    }

    public static <T> ResultRes<T> fail(String msg,T data){
        return ResultRes.<T>builder()
                .code(Constant.RESULT_CODE_FAILURE)
                .msg(msg)
                .data(data)
                .build();
    }

    private static class ResultResBuilder<T> {
        private String code;
        private String msg;
        private T data;

        ResultResBuilder() {

        }

        public ResultResBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        public ResultResBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResultResBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResultRes<T> build() {
            return new ResultRes<>(this.code, this.msg, this.data);
        }
    }
}
