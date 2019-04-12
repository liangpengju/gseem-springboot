package com.gseem.lesson02.rowmapper;

import com.gseem.lesson02.entity.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户查询结果封装类
 */
public class UserRowMapper implements RowMapper<User> {

    /**
     * 封装用户表查询结果
     * @param rs 结果集
     * @param rowNum 记录行
     * @return
     * @throws SQLException
     */
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setAge(rs.getInt("age"));
        return user;
    }
}
