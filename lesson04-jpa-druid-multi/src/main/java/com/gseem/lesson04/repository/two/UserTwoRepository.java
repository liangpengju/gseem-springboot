package com.gseem.lesson04.repository.two;

import com.gseem.lesson04.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author liangpengju-ds
 *  JpaRepository：JPA接口
 */
public interface UserTwoRepository extends JpaRepository<User,Long>{

    /**
     * 根据姓名查找用户列表
     * @param name
     * @return
     */
    List<User> findByName(String name);

    List<User> findByNameOrAge(String name, int age);

    /**
     * 根据姓名查找记录个数
     * 默认使用model查询
     * @param name
     * @return
     */
    @Query("select count(t) from User t where t.name = ?1")
    int findCountByName(String name);

    /**
     * 根据姓名和性别查找
     * nativeQuery = true : 指定使用原生的sql查询
     * @param name
     * @param sex
     * @return
     */
    @Query(value = "select t from user t where t.name = ?1 and t.sex = ?2",nativeQuery = true)
    List<User> findByNameAndSex(String name, String sex);

    /**
     * 分页查找所有记录
     * @param pageable
     * @return
     */
    @Query("select u from User u")
    Page<User> findALL(Pageable pageable);

    /**
     * 分页
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findByName(String name, Pageable pageable);


}
