package com.gseem.lesson04.entity;

import javax.persistence.*;

/**
 * @author liangpengju-ds
 */
@Entity
@Table(name = "user_address")
public class UserAddress {

    @Id
    @GeneratedValue
    private Long id;

    /**省份*/
    private String province;

    /**城市*/
    private String city;

    /**街道*/
    private String street;

    /**邮编*/
    private String zipcode;

    /**联系方式*/
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
