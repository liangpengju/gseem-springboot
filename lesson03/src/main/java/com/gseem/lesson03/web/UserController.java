package com.gseem.lesson03.web;

import com.gseem.lesson03.entity.User;
import com.gseem.lesson03.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户接口
 * 1.@Api注解标注到类上
 * 2.@ApiOperation注解可以标注到类和方法上
 * 3.@ApiImplicitParams、@ApiImplicitParam注解标注到方法参数上
 *
 * @author liangpengju-ds
 */
@Api(value = "用户接口",description = "用户接口API",protocols = "http")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * GET /users
     *
     * @return
     */
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @RequestMapping(value={""}, method= RequestMethod.GET)
    public List<User> getUserList() {
        return userService.selectAll();
    }


    /**
     * 创建用户
     * POST /users
     * @param user
     * @return
     */
    @ApiOperation(
            value="创建用户",
            notes="根据User对象创建用户",
            produces="application/json",
            consumes="application/json",
            response = String.class
    )
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        userService.insert(user);
        return "success";
    }

    /**
     * 根据id获取用户详细信息
     * GET /user/{id}
     * @param id
     * @return
     */
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path",example = "1")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.selectById(id);
    }

    /**
     * 更新用户详细信息
     *  PUT  /user/{id}
     * @param id
     * @param user
     * @return
     */
    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path",example = "1"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @ApiResponses({
            @ApiResponse(code = 100, message = "请求参数有误"),
            @ApiResponse(code = 101, message = "未授权"),
            @ApiResponse(code = 103, message = "禁止访问"),
            @ApiResponse(code = 104, message = "请求路径不存在"),
            @ApiResponse(code = 200, message = "服务器内部错误")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User dbUser = userService.selectById(id);
        if(dbUser == null){
            return "fail";
        }
        BeanUtils.copyProperties(user,dbUser);
        userService.updateByPrimaryKey(dbUser);
        return "success";
    }

    /**
     * 删除用户
     * DELETE  /user/{id}
     * @param id
     * @return
     */
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path",example = "1")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "success";
    }

}
