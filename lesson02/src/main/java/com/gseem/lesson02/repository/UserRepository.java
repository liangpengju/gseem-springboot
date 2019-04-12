package com.gseem.lesson02.repository;

import com.gseem.lesson02.entity.User;

import java.util.List;

/**
 * @author liangpengju
 */
public interface UserRepository {
    /**
     * 新增保存
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 更新修改
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int delete(long id);

    /**
     * 查找全部
     * @return
     */
    List<User> findALL();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(long id);

}
