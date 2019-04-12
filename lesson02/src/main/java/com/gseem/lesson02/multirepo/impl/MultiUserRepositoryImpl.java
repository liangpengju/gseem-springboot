package com.gseem.lesson02.multirepo.impl;

import com.gseem.lesson02.entity.User;
import com.gseem.lesson02.multirepo.MultiUserRepository;
import com.gseem.lesson02.repository.UserRepository;
import com.gseem.lesson02.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类上使用@Repository 注解用于标注数据访问组件
 */
@Repository
public class MultiUserRepositoryImpl implements MultiUserRepository {

    /**
     * SpringJdbc操作工具类
     * 默认按照名称加载 primaryJdbcTemplate
     */
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    private void initJdbcTemplate(JdbcTemplate jdbcTemplate){
        if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
    }

    /**
     * 新增保存
     *
     * @param user
     * @return
     */
    @Override
    public int save(User user,JdbcTemplate jdbcTemplate) {
        initJdbcTemplate(jdbcTemplate);
        return jdbcTemplate.update("INSERT INTO user(name,password,age) VALUES (?, ?, ?)",
                user.getName(), user.getPassword(), user.getAge());
    }

    /**
     * 更新修改
     *
     * @param user
     * @return
     */
    @Override
    public int update(User user,JdbcTemplate jdbcTemplate) {
        initJdbcTemplate(jdbcTemplate);
        return jdbcTemplate.update("UPDATE user SET name = ? , password = ? , age = ? WHERE id=?",
                user.getName(),user.getPassword(),user.getAge(),user.getId());
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public int delete(long id,JdbcTemplate jdbcTemplate) {
        initJdbcTemplate(jdbcTemplate);
        return jdbcTemplate.update("DELETE FROM user where id = ? ",id);
    }

    /**
     * 查找全部
     *
     * @return
     */
    @Override
    public List<User> findALL(JdbcTemplate jdbcTemplate) {
        initJdbcTemplate(jdbcTemplate);
        return jdbcTemplate.query("SELECT * FROM user",new UserRowMapper());
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public User findById(long id,JdbcTemplate jdbcTemplate) {
        initJdbcTemplate(jdbcTemplate);
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ? ",
                new Object[]{id},new BeanPropertyRowMapper<>(User.class));
    }
}
