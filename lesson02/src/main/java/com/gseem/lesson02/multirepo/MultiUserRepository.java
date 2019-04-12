package com.gseem.lesson02.multirepo;

import com.gseem.lesson02.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author liangpengju
 */
public interface MultiUserRepository {
    /**
     * 新增保存
     * @param user
     * @return
     */
    int save(User user, JdbcTemplate jdbcTemplate);

    /**
     * 更新修改
     * @param user
     * @return
     */
    int update(User user,JdbcTemplate jdbcTemplate);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int delete(long id,JdbcTemplate jdbcTemplate);

    /**
     * 查找全部
     * @return
     */
    List<User> findALL(JdbcTemplate jdbcTemplate);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(long id,JdbcTemplate jdbcTemplate);

}
