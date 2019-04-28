package com.gseem.lesson03.mapper;

import com.gseem.lesson03.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

/**
 * @author liangpengju-ds
 */
public interface UserMapper {

    @SelectProvider(type = UserSqlProvider.class, method = "findList")
    List<User> findList(User user);

    @SelectProvider(type = UserSqlProvider.class, method = "getCount")
    int getCount(User user);

    @Select("SELECT * FROM user WHERE name = #{name}")
    List<User> findListByName(@Param("name") String name);

    @Select("SELECT * FROM user WHERE name = #{name} AND sex = #{sex}")
    List<User> findListByNameAndSex(Map<String, Object> map);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select({
            "select",
            "id, name, password, age, sex, birthday, create_time, update_time, status",
            "from user",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
            @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
            @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    User selectByPrimaryKey(Long id);



    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 新增
     * @param record
     * @return
     */
    @Insert({
        "insert into user (id, name, ",
        "password, age, sex, ",
        "birthday, create_time, ",
        "update_time, status)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, #{sex,jdbcType=TINYINT}, ",
        "#{birthday,jdbcType=DATE}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER})"
    })
    int insert(User record);

    /**
     * 自定义动态SQL类插入数据
     * @param record
     * @return
     */
    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    /**
     * 自定义动态sql类，更新
     * @param record
     * @return
     */
    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    @Update({
        "update user",
        "set name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "sex = #{sex,jdbcType=TINYINT},",
          "birthday = #{birthday,jdbcType=DATE},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);


}