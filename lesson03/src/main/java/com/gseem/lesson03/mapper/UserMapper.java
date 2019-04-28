package com.gseem.lesson03.mapper;

import com.gseem.lesson03.entity.User;

import java.util.List;

/**
 * @author liangpengju-ds
 */
public interface UserMapper {

    List<User> selectAll();

    User selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}