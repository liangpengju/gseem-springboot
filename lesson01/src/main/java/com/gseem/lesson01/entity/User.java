package com.gseem.lesson01.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户实体类")
public class User {

    /**id*/
    private Long id;

    /**姓名*/
    @ApiModelProperty(value = "姓名", name = "name", required = true)
    private String name;
    /**年龄*/
    private Integer age;

}
