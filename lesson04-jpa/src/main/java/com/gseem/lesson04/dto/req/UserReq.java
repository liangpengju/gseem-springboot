package com.gseem.lesson04.dto.req;

import lombok.Data;

/**
 * 查询条件
 * @author liangpengju-ds
 */
@Data
public class UserReq {

    /**姓名*/
    private String name;
    /**最小年龄*/
    private Integer minAge;
    /**最大年龄*/
    private Integer maxAge;
    /**城市*/
    private String city;

    private Integer page = 1;

    private Integer pageSize = 10;
}
