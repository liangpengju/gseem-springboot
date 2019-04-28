package com.gseem.lesson03.mapper;

import com.gseem.lesson03.dto.PageParam;
import com.gseem.lesson03.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @author liangpengju-ds
 */
@Slf4j
public class UserSqlProvider {

    /**
     * 处理where条件
     * @param user
     * @param sql
     */
    private void whereSql(User user, StringBuffer sql){
        if (user != null) {
            if (!StringUtils.isEmpty(user.getId())) {
                sql.append(" and id = #{id}");
            }
            if (!StringUtils.isEmpty(user.getName())) {
                sql.append(" and name = #{name}");
            }
            if (!StringUtils.isEmpty(user.getAge())) {
                sql.append(" and age >= #{age}");
            }
            if (!StringUtils.isEmpty(user.getSex())) {
                sql.append(" and sex = #{sex}");
            }
            if (!StringUtils.isEmpty(user.getStatus())) {
                sql.append(" and status = #{status}");
            }
        }
    }

    /**
     * 根据条件查询
     * @param user
     * @return
     */
    public String findList(User user) {
        StringBuffer sql = new StringBuffer("select id, name, password, age, sex, birthday, create_time, update_time, status");
        sql.append(" from user where 1=1 ");
        //where
        whereSql(user,sql);
        //排序
        sql.append(" order by id desc");
        //分页
        PageParam pageParam = new PageParam(0,10);
        sql.append(" limit " + pageParam.getStart() + "," + pageParam.getPageSize());

        log.info("findList sql is :" + sql.toString());
        return sql.toString();
    }

    /**
     * 获取记录数目
     * @param user
     * @return
     */
    public String getCount(User user) {
        String sql= new SQL(){{
            SELECT("count(1)");
            FROM("user");
            if (!StringUtils.isEmpty(user.getName())) {
                WHERE("name = #{name}");
            }
            if (!StringUtils.isEmpty(user.getId())) {
                WHERE("id = #{id}");
            }
            if (!StringUtils.isEmpty(user.getAge())) {
                WHERE("age >= #{age}");
            }
            if (!StringUtils.isEmpty(user.getSex())) {
                WHERE("sex = #{sex}");
            }
            if (!StringUtils.isEmpty(user.getStatus())) {
                WHERE("status >= #{status}");
            }
            //从这个toString可以看出，其内部使用高效的StringBuilder实现SQL拼接
        }}.toString();

        log.info("getCount sql is :" +sql);
        return sql;
    }

    /**
     * 选择非空新增
     * @param record
     * @return
     */
    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getAge() != null) {
            sql.VALUES("age", "#{age,jdbcType=INTEGER}");
        }
        
        if (record.getSex() != null) {
            sql.VALUES("sex", "#{sex,jdbcType=TINYINT}");
        }
        
        if (record.getBirthday() != null) {
            sql.VALUES("birthday", "#{birthday,jdbcType=DATE}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    /**
     * 更新非空字段
     * @param record
     * @return
     */
    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("user");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getAge() != null) {
            sql.SET("age = #{age,jdbcType=INTEGER}");
        }
        
        if (record.getSex() != null) {
            sql.SET("sex = #{sex,jdbcType=TINYINT}");
        }
        
        if (record.getBirthday() != null) {
            sql.SET("birthday = #{birthday,jdbcType=DATE}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}