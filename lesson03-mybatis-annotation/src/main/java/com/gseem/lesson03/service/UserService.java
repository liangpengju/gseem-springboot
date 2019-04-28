package com.gseem.lesson03.service;

import com.gseem.lesson03.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liangpengju-ds
 */
public interface UserService {

    int count(User user);

    List<User> selectList(User user);

    List<User> findListByNameAndSex(String name,Integer sex);

    List<User> findListByName(@Param("name") String name);

    User selectById(Long id);

    int deleteById(Long id);

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
