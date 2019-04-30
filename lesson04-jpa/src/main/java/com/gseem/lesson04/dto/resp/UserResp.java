package com.gseem.lesson04.dto.resp;

import lombok.Data;

import java.util.Date;

/**
 * 响应数据
 * @author liangpengju-ds
 */
@Data
public class UserResp {

    private Long id;

    private String name;

    private String password;

    private Integer age;

    private Integer sex;

    private String birthday;

    private String createTime;

    private String updateTime;

    private Integer status;

    //地址信息
    /**省份*/
    private String province;

    /**城市*/
    private String city;

    /**街道*/
    private String street;

    /**邮编*/
    private String zipcode;


}
