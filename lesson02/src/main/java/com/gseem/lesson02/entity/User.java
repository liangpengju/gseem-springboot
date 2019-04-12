package com.gseem.lesson02.entity;

import lombok.Data;

@Data
public class User {

    /**主键id，自增长*/
    private Long id;
    /**用户名*/
    private String name;
    /**密码*/
    private String password;
    /**年龄*/
    private int age;

    public User() {
    }

    public User(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }
}
