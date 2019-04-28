package com.gseem.lesson03.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liangpengju
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String password;

    private Integer age;

    private Byte sex;

    private Date birthday;

    private Date createTime;

    private Date updateTime;

    private Integer status;

}