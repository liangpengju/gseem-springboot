package com.gseem.lesson03.dto;

import lombok.Data;

/**
 * @author liangpengju-ds
 */
@Data
public class UserParam extends PageParam{
    /**用户名*/
    private String name;
    /**密码*/
    private String password;
}
