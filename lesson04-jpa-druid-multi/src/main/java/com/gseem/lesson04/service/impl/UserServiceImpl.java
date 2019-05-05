package com.gseem.lesson04.service.impl;

import com.gseem.lesson04.entity.User;
import com.gseem.lesson04.repository.one.UserOneRepository;
import com.gseem.lesson04.repository.two.UserTwoRepository;
import com.gseem.lesson04.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author liangpengju-ds
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserOneRepository userOneRepository;
    @Autowired
    private UserTwoRepository userTwoRepository;

    @Override
    public List<User> findAllOne() {
        return userOneRepository.findAll();
    }

    @Override
    public List<User> findAllTwo() {
        return userTwoRepository.findAll();
    }

}
