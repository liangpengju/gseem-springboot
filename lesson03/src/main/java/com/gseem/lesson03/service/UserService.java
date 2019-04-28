package com.gseem.lesson03.service;

import com.gseem.lesson03.entity.User;

import java.util.List;

/**
 * @author liangpengju-ds
 */
public interface UserService {

    List<User> selectAll();

    User selectById(Long id);

    int deleteById(Long id);

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
