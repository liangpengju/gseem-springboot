package com.gseem.lesson03.service.impl;

import com.gseem.lesson03.entity.User;
import com.gseem.lesson03.mapper.UserMapper;
import com.gseem.lesson03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liangpengju-ds
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int count(User user) {
        return userMapper.getCount(user);
    }

    @Override
    public List<User> selectList(User user) {
        return userMapper.findList(user);
    }

    @Override
    public List<User> findListByNameAndSex(String name,Integer sex) {
        Map<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("sex",sex);
        return userMapper.findListByNameAndSex(map);
    }

    @Override
    public List<User> findListByName(String name) {
        return userMapper.findListByName(name);
    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }


    @Override
    public int deleteById(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
