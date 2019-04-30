package com.gseem.lesson04.service;

import com.gseem.lesson04.dto.req.UserReq;
import com.gseem.lesson04.dto.resp.PagingRes;
import com.gseem.lesson04.entity.User;
import com.gseem.lesson04.util.ResultRes;

/**
 * @author liangpengju-ds
 */
public interface UserService {

     /**
      * 更新用户
      * @param user
      * @return
      */
     ResultRes updateUser(User user);

     /**
      * 组合条件查询
      * @param req
      * @return
      */
     PagingRes<User> queryUserBySpecification(UserReq req);


}
