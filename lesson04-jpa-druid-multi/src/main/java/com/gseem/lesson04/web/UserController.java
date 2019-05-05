package com.gseem.lesson04.web;

import com.gseem.lesson04.entity.User;
import com.gseem.lesson04.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liangpengju-ds
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表1
     * @return
     */
    @RequestMapping(value = "/listOne")
    public List<User> listOne(){
       return userService.findAllOne();
    }

    /**
     * 用户列表2
     * @return
     */
    @RequestMapping(value = "/listTwo")
    public List<User> listTwo(){
        return userService.findAllTwo();
    }

}
