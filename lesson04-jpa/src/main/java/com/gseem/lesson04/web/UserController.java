package com.gseem.lesson04.web;

import com.gseem.lesson04.dto.req.UserReq;
import com.gseem.lesson04.dto.resp.PagingRes;
import com.gseem.lesson04.entity.User;
import com.gseem.lesson04.service.UserService;
import com.gseem.lesson04.util.ResultRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangpengju-ds
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表
     * @param req
     * @return
     */
    @RequestMapping(value = "/list")
    public PagingRes<User> list(UserReq req){
       return userService.queryUserBySpecification(req);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping(value = "/update")
    public ResultRes updateUser(User user){
        return userService.updateUser(user);
    }

}
