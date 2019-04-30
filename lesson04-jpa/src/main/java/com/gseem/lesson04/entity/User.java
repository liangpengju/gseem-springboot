package com.gseem.lesson04.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liangpengju-ds
 */
@Data
@Entity(name = "User")
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "User.findByPassword", query = "select u from User u where u.password = ?1"),
        @NamedQuery(name = "User.findByStatus", query = "select u from User u where u.status = ?1"),
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    private String password;

    private Integer age;

    private Byte sex;

    private Date birthday;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private Integer status;

}