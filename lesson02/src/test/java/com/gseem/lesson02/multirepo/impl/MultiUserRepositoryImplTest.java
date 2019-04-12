package com.gseem.lesson02.multirepo.impl;

import com.gseem.lesson02.entity.User;
import com.gseem.lesson02.multirepo.MultiUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiUserRepositoryImplTest {

    @Autowired
    private MultiUserRepository multiUserRepository;
    /**数据源1*/
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;
    /**数据源2*/
    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    @Test
    public void testSave(){
        User user1 = new User("feiyue","123",20);
        User user2 = new User("cloud","123456",26);
        multiUserRepository.save(user1,primaryJdbcTemplate);
        multiUserRepository.save(user2,secondaryJdbcTemplate);
    }



}















